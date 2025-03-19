package com.ahq.listeners;

import com.ahq.internal.ExtentReportManager;
//import com.ahq.utils.GmailUtils;
import com.ahq.internal.StringManipulation;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qmetry.qaf.automation.core.QAFTestBase;
import com.qmetry.qaf.automation.core.TestBaseProvider;
import com.qmetry.qaf.automation.step.QAFTestStepListener;
import com.qmetry.qaf.automation.step.StepExecutionTracker;
import com.qmetry.qaf.automation.step.TestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriver;
import org.openqa.selenium.OutputType;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

import static com.ahq.globals.utilities.UtilPassword.SECRET_STRINGS;


public class StepListener implements QAFTestStepListener {

    public static ThreadLocal<List<StepExecutionTracker>> threadedExecutedTestSteps = new ThreadLocal<>();
    public static ThreadLocal<List<ExtentTest>> threadedExtentTestNodes = new ThreadLocal<>();
    private static final List<String> excludedSteps = List.of(
            "I get attribute {attribute_name} value from {locator}",
            "I wait until element/field {0} is present");

    private Boolean excludedStepsStartsWith(String step) {
        return step.startsWith("I wait") || step.startsWith("I switch") || step.startsWith("I scroll");
    }
    @Override
    public void onFailure(StepExecutionTracker stepExecutionTracker) {
    }




    @Override
    public void beforExecute(StepExecutionTracker stepExecutionTracker) {
        if (stepExecutionTracker.getType().startsWith("Step") || stepExecutionTracker.getType().startsWith("Result") || stepExecutionTracker.getType().startsWith("And") || stepExecutionTracker.getType().startsWith("*") || stepExecutionTracker.getType().startsWith("Given") || stepExecutionTracker.getType().startsWith("When") || stepExecutionTracker.getType().startsWith("Then")|| stepExecutionTracker.getType().startsWith("But")) {
            String stepDescription = StringManipulation.cleanBDDActions(stepExecutionTracker.getStep().getDescription());
            threadedExtentTestNodes.get().add(ExtentReportManager.getTest().createNode(stepDescription));
            threadedExecutedTestSteps.get().add(stepExecutionTracker);
        }
    }

    @Override
    public void afterExecute(StepExecutionTracker stepExecutionTracker) {
        if ((stepExecutionTracker.getStep().getFileName().equalsIgnoreCase("BrowserGlobal.java") || stepExecutionTracker.getStep().getFileName().equalsIgnoreCase("D365CRM.java")) && !excludedStepsStartsWith(stepExecutionTracker.getStep().getDescription()) && !excludedSteps.contains(stepExecutionTracker.getStep().getDescription())) {
            String augmentedStepDescription = formatStepDescriptionWithArgs(stepExecutionTracker.getStep());
            QAFTestBase testBase = TestBaseProvider.instance().get();
            String assertLog = testBase.getAssertionsLog();
            String pattern = "/img/\\w+\\.png";
            Pattern regexPattern = Pattern.compile(pattern);
            Matcher matcher = regexPattern.matcher(assertLog);
            boolean hasScreenshot = matcher.find();
            // Assertion failures or major breakages will throw an exception
            boolean failedWithAssertionOrException = stepExecutionTracker.hasException();
            // All verification steps should return a boolean result. No other known way to determine if a verification step has failed.
            // Not sure how to retrieve the actual result of the verification step, only the expected result.
            boolean failedWithFalseVerification = stepExecutionTracker.getResult() != null && stepExecutionTracker.getResult().equals(false);
            boolean hasStepFailed = failedWithAssertionOrException || failedWithFalseVerification || augmentedStepDescription.contains("I fail step with info");
            QAFWebDriver driver = new WebDriverTestBase().getDriver();
            if (getBundle().getPropertyValue("exec.email.report.fail").equalsIgnoreCase("OFF")) {
//                System.out.println("========STEP FAIL IGNORED============>");
            } else if (hasStepFailed) {
                if (stepExecutionTracker.hasException()) {
                    Throwable throwable = stepExecutionTracker.getException();
                    threadedExtentTestNodes.get().get(threadedExtentTestNodes.get().size() - 1).log(Status.FAIL, throwable, MediaEntityBuilder.createScreenCaptureFromBase64String(driver.getScreenshotAs(OutputType.BASE64)).build());
                } else {
                    threadedExtentTestNodes.get().get(threadedExtentTestNodes.get().size() - 1).log(Status.FAIL, augmentedStepDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(driver.getScreenshotAs(OutputType.BASE64)).build());
                }
            } else {
                if (augmentedStepDescription.contains("didn't load all fields properly on first attempt")) {
                    // Separately handle this step to notify of a known attachment issue caused a retry
                    threadedExtentTestNodes.get().get(threadedExtentTestNodes.get().size() - 1).log(Status.INFO, MarkupHelper.createLabel(augmentedStepDescription, ExtentColor.BLUE));
                } else {
                    if (hasScreenshot) {
                        threadedExtentTestNodes.get().get(threadedExtentTestNodes.get().size() - 1).log(Status.PASS, augmentedStepDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(driver.getScreenshotAs(OutputType.BASE64)).build());
                    } else {
                        threadedExtentTestNodes.get().get(threadedExtentTestNodes.get().size() - 1).log(Status.PASS, augmentedStepDescription);
                    }
                }
            }
        }
    }


    private String formatStepDescriptionWithArgs(TestStep step) {
        String description = step.getDescription();
        Object[] actualArgs = step.getActualArgs();
        if (actualArgs != null) {
            for (int i = 0; i < actualArgs.length; i++) {
                if (SECRET_STRINGS.contains(actualArgs[i].toString())) {
                    actualArgs[i] = "***";
                }
                if (actualArgs[i].toString().startsWith("loc.")) {
                    actualArgs[i] = cleanLocatorParameter(actualArgs[i].toString());
                }
            }
            description = description.replaceAll("\\{.*?\\}", "\"%s\"");
            if (description.contains("%s")) {
                description = String.format(description, actualArgs);
            }
        }
        return description;
    }

    private String cleanLocatorParameter(String string) {
        String[] splitString = string.split("\\.");
        String elementType = splitString[2];
        String element = splitString[3];
        return element + " (" + elementType + ")";
    }
}






// /// /// OLD CODE===============================

//package com.ahq.listeners;
//
//import com.ahq.addons.stringManipulation;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.qmetry.qaf.automation.core.QAFTestBase;
//import com.qmetry.qaf.automation.core.TestBaseProvider;
//import com.qmetry.qaf.automation.step.QAFTestStepListener;
//import com.qmetry.qaf.automation.step.StepExecutionTracker;
//import com.qmetry.qaf.automation.step.TestStep;
//import com.qmetry.qaf.automation.ui.WebDriverTestBase;
//import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriver;
//import org.openqa.selenium.OutputType;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static com.ahq.globals.utilities.UtilPassword.SECRET_STRINGS;
//
//
//public class stepListener implements QAFTestStepListener {
//
//    public static List<StepExecutionTracker> executedTestSteps = new ArrayList<>();
//    private static final List<ExtentTest> extentTestNodes = new ArrayList<>();
//    private static final List<String> excludedSteps = List.of(
//            "I wait for {millisecs} milliseconds",
//            "I wait for {secs} seconds",
//            "I scroll to an element {locator}",
//            "I switch to iFrame by id or name {id/name}",
//            "I switch to iFrame by index {index}",
//            "I switch to iFrame by title {title}",
//            "I switch to iFrame by locator {locator}");
//
//    @Override
//    public void onFailure(StepExecutionTracker stepExecutionTracker) {
//    }
//
//    @Override
//    public void beforExecute(StepExecutionTracker stepExecutionTracker) {
//        if (stepExecutionTracker.getType().equals("Step:") || stepExecutionTracker.getType().equals("Result:") || stepExecutionTracker.getType().equals("And:") || stepExecutionTracker.getType().equals("*")) {
//            String stepDescription = stringManipulation.cleanBDDActions(stepExecutionTracker.getStep().getDescription());
//            extentTestNodes.add(testListener.extentTest.createNode(stepDescription));
//            executedTestSteps.add(stepExecutionTracker);
//        }
//    }
//
//    @Override
//    public void afterExecute(StepExecutionTracker stepExecutionTracker) {
//        if (stepExecutionTracker.getStep().getFileName().equals("BrowserGlobal.java") && !excludedSteps.contains(stepExecutionTracker.getStep().getDescription())) {
//            String augmentedStepDescription = formatStepDescriptionWithArgs(stepExecutionTracker.getStep());
//            QAFTestBase testBase = TestBaseProvider.instance().get();
//            String assertLog = testBase.getAssertionsLog();
//            boolean hasStepPassed = assertLog.contains("class=\"teststeppass\"") || assertLog.contains("class=\"pass\"");
//            String pattern = "/img/\\w+\\.png";
//            Pattern regexPattern = Pattern.compile(pattern);
//            Matcher matcher = regexPattern.matcher(assertLog);
//            boolean hasScreenshot = matcher.find();
//            stepExecutionTracker.setSuccess(hasStepPassed);
//            QAFWebDriver driver = new WebDriverTestBase().getDriver();
//            if (hasStepPassed) {
//                if (augmentedStepDescription.contains("didn't load all fields properly on first attempt")) {
//                    // Separately handle this step to notify of a known attachment issue caused a retry
//                    extentTestNodes.get(extentTestNodes.size() - 1).info(MarkupHelper.createLabel(augmentedStepDescription, ExtentColor.BLUE));
//                } else {
//                    if (hasScreenshot) {
//                        extentTestNodes.get(extentTestNodes.size() - 1).pass(augmentedStepDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(driver.getScreenshotAs(OutputType.BASE64)).build());
//                    } else {
//                        extentTestNodes.get(extentTestNodes.size() - 1).pass(augmentedStepDescription);
//                    }
//                }
//            } else {
//                if (stepExecutionTracker.hasException()) {
//                    Throwable throwable = stepExecutionTracker.getException();
//                    extentTestNodes.get(extentTestNodes.size() - 1).fail(throwable, MediaEntityBuilder.createScreenCaptureFromBase64String(driver.getScreenshotAs(OutputType.BASE64)).build());
//                } else {
//                    extentTestNodes.get(extentTestNodes.size() - 1).fail(augmentedStepDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(driver.getScreenshotAs(OutputType.BASE64)).build());
//                }
//            }
//        }
//    }
//
//    private String formatStepDescriptionWithArgs(TestStep step) {
//        String description = step.getDescription();
//        Object[] actualArgs = step.getActualArgs();
//        if (actualArgs != null) {
//            for (int i = 0; i < actualArgs.length; i++) {
//                if (SECRET_STRINGS.contains(actualArgs[i].toString())) {
//                    actualArgs[i] = "***";
//                }
//                if (actualArgs[i].toString().startsWith("loc.")) {
//                    actualArgs[i] = cleanLocatorParameter(actualArgs[i].toString());
//                }
//            }
//            description = description.replaceAll("\\{.*?\\}", "\"%s\"");
//            if (description.contains("\\%s")) {
//                description = String.format(description, actualArgs);
//            }
//        }
//        return description;
//    }
//
//    private String cleanLocatorParameter(String string) {
//        String[] splitString = string.split("\\.");
//        String elementType = splitString[2];
//        String element = splitString[3];
//        return element + " (" + elementType + ")";
//    }
//}
