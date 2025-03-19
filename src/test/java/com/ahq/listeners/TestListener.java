package com.ahq.listeners;

import com.ahq.globals.Utils;
import com.ahq.internal.ExtentReportManager;
import com.ahq.internal.StringManipulation;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qmetry.qaf.automation.step.TestStep;
import com.qmetry.qaf.automation.step.client.Scenario;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriver;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;


    public class TestListener implements ITestListener {

        private static final ThreadLocal<List<String>> threadedCompleteStepDescriptionList = new ThreadLocal<>();
        private static final ThreadLocal<List<String>> threadedTestCategories = new ThreadLocal<>();

        @Override
        public void onTestStart(ITestResult iTestResult) {
//            String time = Utils.dateTime_currentDateWithFormat("")
            Object instance = iTestResult.getInstance();
            if (instance instanceof Scenario) {
                assignParametersToBundleProperties(iTestResult.getParameters());
                Scenario scenario = (Scenario) instance;
                threadedCompleteStepDescriptionList.set(new ArrayList<>());
                for (TestStep step : scenario.getSteps()) {
                    threadedCompleteStepDescriptionList.get().add(step.getDescription());
                }
                try {
                    ExtentReportManager.createTest(iTestResult.getMethod().getMethodName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }                Map<String, Object> metaData = scenario.getMetadata();
                // Iterate through the map
                threadedTestCategories.set(new ArrayList<>());
                for (Map.Entry<String, Object> entry : metaData.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue().toString();
                    // Check if the key contains "TagGroup"
                    if (key.contains("TagGroup")) {
                        threadedTestCategories.get().add(value);
                    }
                }
                StepListener.threadedExtentTestNodes.set(new ArrayList<>());
                StepListener.threadedExecutedTestSteps.set(new ArrayList<>());
            } else {
                System.out.println("The object is not an instance of a feature Scenario. Extent Report will not be created.");
            }
        }

        private void assignParametersToBundleProperties(Object[] parameters) {
            LinkedHashMap<String, Object> parameterMap;
            if (parameters != null && parameters.length > 0 && parameters[0] instanceof LinkedHashMap) {
                parameterMap = (LinkedHashMap<String, Object>) parameters[0];
                for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                    getBundle().setProperty(entry.getKey(), entry.getValue());
                }
            }
        }

        @Override
        public void onTestSuccess(ITestResult iTestResult) {
            if (ExtentReportManager.getTest() != null) {
                logReportSteps();
                ExtentReportManager.getTest().pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
                logTestCategories();
            }
        }

        @Override
        public void onTestFailure(ITestResult iTestResult) {
            if (ExtentReportManager.getTest() != null) {
                logReportSteps();
                ExtentReportManager.getTest().fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
                QAFWebDriver driver = new WebDriverTestBase().getDriver();
                ExtentReportManager.getTest().addScreenCaptureFromBase64String(driver.getScreenshotAs(OutputType.BASE64));
                logTestCategories();
            }
        }

        @Override
        public void onTestSkipped(ITestResult iTestResult) {
            if (ExtentReportManager.getTest() != null) {
                logReportSteps();
                Throwable throwable = iTestResult.getThrowable();
                if (throwable != null) {
                    ExtentReportManager.getTest().skip(throwable);
                } else {
                    ExtentReportManager.getTest().skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
                }
                QAFWebDriver driver = new WebDriverTestBase().getDriver();
                ExtentReportManager.getTest().addScreenCaptureFromBase64String(driver.getScreenshotAs(OutputType.BASE64));
                logTestCategories();
            }
        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        }

        @Override
        public void onStart(ITestContext iTestContext) {
            if (iTestContext.getName().toLowerCase().contains("smoke")) {
                System.out.println("Executing Smoke Test suite...");
                getBundle().setProperty("suite", "smoke");
            } else if (iTestContext.getName().toLowerCase().contains("regression")) {
                System.out.println("Executing Regression Test suite...");
                getBundle().setProperty("suite", "regression");
            } else {
                System.out.println("Executing Test suite...");
            }
        }

        @Override
        public void onFinish(ITestContext iTestContext) {
            try {
                ExtentReportManager.flushReports();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // To make emailable report without outside file link
            try {
                Path path = Paths.get("reports/email-report.html");
                Path path_2 = Paths.get("reports/do_not_copy/replace_file_1.txt");
                Path path_3 = Paths.get("reports/do_not_copy/replace_file_2.txt");

                Charset charset = StandardCharsets.UTF_8;

                String content = Files.readString(path, charset);
                String replaceContent1 = Files.readString(path_2, charset);
                String replaceContent2 = Files.readString(path_3, charset);
                content = content.replaceAll("<link rel=\"apple-touch-icon\" href=\"https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@b00a2d0486596e73dd7326beacf352c639623a0e/commons/img/logo.png\">", "<link href=\"data:image/x-icon;base64,iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAMAAABrrFhUAAAAIGNIUk0AAHomAACAhAAA+gAAAIDoAAB1MAAA6mAAADqYAAAXcJy6UTwAAAA8UExURQAAAACX3wCZ2wCX3ACZ3ACZ2wCX2wCZ3ACZ3QCY3ACa3wCZ2wCY2wCZ3ACf3wCY3ACY3ACZ3ACZ3P////BAFBcAAAASdFJOUwAgz2DgwEDvcJAwgNDwEN+wUKEP6nEAAAABYktHRBMMu1yWAAAAB3RJTUUH6AgJAhAK0igYJQAAAYxJREFUeNrt3ctOg1AUQFF8P6pW/f+Pdb4ZnDRcY2LXHrecw2JGCGybJEmSJEmSJEmSJEmSJEmSJEnS0E26Tavn3aXvofsEAAAAAAAAAAAAAAAAAAAAADjeQ+oCj+ko8FOaAI7OBwAAAAAAAAAAAAAAAAAAAABs23OaFnhJq4FXzwMAAAAAAAAAAAAAAAAAAAAAbNtrmhZo/f8075Sm47+l5VccAAAAAAAAAAAAAAAAAAAAuEKADpgW6gmsBn5PlwIDAAAAAAAAAAAAAAAAAAAAAPb1AYNpoT7AMB3/I/WFC9O8PqCx/IoDAAAAAAAAAAAAAAAAAAAAVwDQFwxMC/SGRF9w8NfAAAAAAAAAAAAAAAAAAAAAAIB9vSFxTtNC/cDBpfOnDyQUuPsCAAAAAAAAAAAAAAAAAAAAAC7vM00n3N+vvgCd95UOnzAAAAAAAAAAAAAAAAAAAAAAYPeBhNPQ8hsS6ddPGAAAAAAAAAAAAAAAAAAAAAAgSZIkSZIkSZIkSZIkSZIkSZL+Xz944OcuccoklAAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAyNC0wOC0wOVQwMjoxNjowOSswMDowML2zubYAAAAldEVYdGRhdGU6bW9kaWZ5ADIwMjQtMDgtMDlUMDI6MTY6MDkrMDA6MDDM7gEKAAAAKHRFWHRkYXRlOnRpbWVzdGFtcAAyMDI0LTA4LTA5VDAyOjE2OjEwKzAwOjAwwsllmAAAAABJRU5ErkJggg==\" rel=\"icon\" type=\"image/x-icon\">");
                content = content.replaceAll("<link rel=\"shortcut icon\" href=\"https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@b00a2d0486596e73dd7326beacf352c639623a0e/commons/img/logo.png\">\n", "");
                content = content.replaceAll("<link href=\"https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@ce8b10435bcbae260c334c0d0c6b61d2c19b6168/spark/css/spark-style.css\" rel=\"stylesheet\" />", replaceContent1);
                content = content.replaceAll("<link href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\">", "");
                content = content.replaceAll("<script src=\"https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@7cc78ce/spark/js/jsontree.js\"></script>", "<script>\n" +
                        "    /*! json-tree - v0.2.2 - 2017-09-25, MIT LICENSE */\n" +
                        "var JSONTree=function(){var n={\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",'\"':\"&quot;\",\"'\":\"&#x27;\",\"/\":\"&#x2F;\"},t=0,r=0;this.create=function(n,t){return r+=1,N(u(n,0,!1),{class:\"jstValue\"})};var e=function(t){return t.replace(/[&<>'\"]/g,function(t){return n[t]})},s=function(){return r+\"_\"+t++},u=function(n,t,r){if(null===n)return f(r?t:0);switch(typeof n){case\"boolean\":return l(n,r?t:0);case\"number\":return i(n,r?t:0);case\"string\":return o(n,r?t:0);default:return n instanceof Array?a(n,t,r):c(n,t,r)}},c=function(n,t,r){var e=s(),u=Object.keys(n).map(function(r){return j(r,n[r],t+1,!0)}).join(m()),c=[g(\"{\",r?t:0,e),N(u,{id:e}),p(\"}\",t)].join(\"\\n\");return N(c,{})},a=function(n,t,r){var e=s(),c=n.map(function(n){return u(n,t+1,!0)}).join(m());return[g(\"[\",r?t:0,e),N(c,{id:e}),p(\"]\",t)].join(\"\\n\")},o=function(n,t){var r=e(JSON.stringify(n));return N(v(r,t),{class:\"jstStr\"})},i=function(n,t){return N(v(n,t),{class:\"jstNum\"})},l=function(n,t){return N(v(n,t),{class:\"jstBool\"})},f=function(n){return N(v(\"null\",n),{class:\"jstNull\"})},j=function(n,t,r){var s=v(e(JSON.stringify(n))+\": \",r),c=N(u(t,r,!1),{});return N(s+c,{class:\"jstProperty\"})},m=function(){return N(\",\\n\",{class:\"jstComma\"})},N=function(n,t){return d(\"span\",t,n)},d=function(n,t,r){return\"<\"+n+Object.keys(t).map(function(n){return\" \"+n+'=\"'+t[n]+'\"'}).join(\"\")+\">\"+r+\"</\"+n+\">\"},g=function(n,t,r){return N(v(n,t),{class:\"jstBracket\"})+N(\"\",{class:\"jstFold\",onclick:\"JSONTree.toggle('\"+r+\"')\"})};this.toggle=function(n){var t=document.getElementById(n),r=t.parentNode,e=t.previousElementSibling;\"\"===t.className?(t.className=\"jstHiddenBlock\",r.className=\"jstFolded\",e.className=\"jstExpand\"):(t.className=\"\",r.className=\"\",e.className=\"jstFold\")};var p=function(n,t){return N(v(n,t),{})},v=function(n,t){return Array(2*t+1).join(\" \")+n};return this}();\n" +
                        "</script>");
                content = content.replaceAll("<i class=\"search-icon fa fa-search\">", "<i class=\"search-icon\"><img style=\"width:50%;\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAYZJREFUWEfFl2uywiAMhct+YA3qyq6u7OIaYD/YMKWDmKctY/91eOTr4ZCkblE8KaWrc+5vm3rtlsRSyhPeQwh3xVYfUxy3KKV07wKL+5dSHlYQFMAaeCSzgHwAHA3eYLQQbwBnBbdA7ABnB+8gbiGESBloB8g5F8Fl4PjH5vgIN2NZlnojJKN670mz1wHp69erxn6FYj15OyoA9/Ucfa/Yliv+KRWpfRxHr3VyC8pBUCqSANbgDSLnDCr02bINRe/9bVTIUQu+BWBUIAFQ90vGo876NACt+TAQytTYnnAEP1cANc2BI6AqqM2Eq5PRBVJNpq41ZWq4htBsoAnEqsI3OeX3mVBZC9hO53AtkOoBjLdKCN6A8tqq4Xp8FyLzvdmFTMWaPC4ZTzuOQUztiDCwEWJaT8ip0kNM6Yo1R9IgzvwvaH0fVopRJqgNLEBn0PrXMzh+bzS3LFffpc5oIIkqAI2klvasmzsHwKLEFAW0uQWObiqApITahFYPYPOHelF/ciClvwBL0UMazRgz/gAAAABJRU5ErkJggg==\"></i>");
                content = content.replaceAll("<i class=\"search-icon-close fa fa-close\">", "<i class=\"search-icon-close\"><img style=\"width:50%;\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAXRJREFUWEfNV+1xwzAIFfvIM6SdLOlkdWaw91EqXfBhjBDg3NX+5ZMEPMQTH5D++QPJ/rIsDwC4v/fmnPN3BCfRM5dSntM0PbieA4B1XX9TSl/soBuEVc8OgCA0EzBmEFRPKeUHAG49PRsAZnwz5g0HN47X3tPfAIyMjPYxXD3j2n4DQAS71zwCMTJOQBT8zzkDAmiLNV4SU1GgB8JqnDmbNgBUsRfEG1x7NV7Z+rxFEjoVNQxOmS3U3WfoUeg5+/ccdzxTE5FFcfU+whvklZiKPaTSUvTo5VRZEQBn6+gmztSTLoAzICyeqyGgHnnD4TGuhsCaXvn1fxSA1/tRxpS4ck0SRj2PhONaicjj+ZkCRnvMaxSjqOe1sETLMWbXXUPCK5WVVB4H6NlDR1QN1sVIbreC4O3fNZpSrTP2plZLW06r6+66ubE6TkVGND4D0EmLl/ZDvBkIpIN5KpKKGK5JfYVGuDZORZoRTyVVGxKt3frU3gtADiQ/T8p3mAAAAABJRU5ErkJggg==\"></i>");

                //List Icon
                content = content.replaceAll("<span class=\"ico\"><i class=\"fa fa-list\"></i></span>", "<svg width=\"20\" height=\"20\" viewBox=\"0 0 35 35\"><path fill=\"#737373\" d=\"M6 22h4v4H6zm0-8h4v4H6zm0 16h4v4H6zM6 6h4v4H6zm0 32h4v4H6zm8-16h28v4H14zm0-8h28v4H14zm0 16h28v4H14zm0-24h28v4H14zm0 32h28v4H14z\"/></svg>");

                // Tag Icon
                content = content.replaceAll("<span class=\"ico\"><i class=\"fa fa-tag\"></i></span>", "<svg width=\"20\" height=\"20\" viewBox=\"0 0 16 16\"><path fill=\"#737373\" d=\"M2 1a1 1 0 0 0-1 1v4.586a1 1 0 0 0 .293.707l7 7a1 1 0 0 0 1.414 0l4.586-4.586a1 1 0 0 0 0-1.414l-7-7A1 1 0 0 0 6.586 1zm4 3.5a1.5 1.5 0 1 1-3 0a1.5 1.5 0 0 1 3 0\"/></svg>");

                content = content.replaceAll("<span class=\"ico\"><i class=\"fa fa-bar-chart\"></i></span>", "<svg width=\"20\" height=\"20\" viewBox=\"0 0 36 36\"><path fill=\"#737373\" d=\"M32 5H4a2 2 0 0 0-2 2v22a2 2 0 0 0 2 2h28a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2M4 29V7h28v22Z\" class=\"clr-i-outline clr-i-outline-path-1\"/><path fill=\"#737373\" d=\"M17 27a8 8 0 0 1 0-16v8h8a8 8 0 0 1-8 8m6.247-6.6H15.4v-7.598A6.4 6.4 0 0 0 17 25.4a6.4 6.4 0 0 0 6.247-5\" class=\"clr-i-outline clr-i-outline-path-2\"/><path fill=\"#737373\" d=\"M19 9a8 8 0 0 1 8 8h-8Zm6.198 6.4a6.41 6.41 0 0 0-4.598-4.599V15.4Z\" class=\"clr-i-outline clr-i-outline-path-3\"/><path fill=\"none\" d=\"M0 0h36v36H0z\"/></svg>");


                content = content.replaceAll("<ul class=\"tools pull-left\">", "<ul class=\"tools pull-left\" style=\"float: left; font-weight: bold;\">");
                content = content.replaceAll("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-exclamation-circle\"></i></a>", "<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i><svg width=\"15\" height=\"15\" viewBox=\"0 0 26 26\"><g fill=\"#737373\"><path fill-rule=\"evenodd\" d=\"M13 5a2 2 0 0 1 2 2v7a2 2 0 1 1-4 0V7a2 2 0 0 1 2-2\" clip-rule=\"evenodd\"/><path d=\"M15 19a2 2 0 1 1-4 0a2 2 0 0 1 4 0\"/><path fill-rule=\"evenodd\" d=\"M13 24c6.075 0 11-4.925 11-11S19.075 2 13 2S2 6.925 2 13s4.925 11 11 11m0 2c7.18 0 13-5.82 13-13S20.18 0 13 0S0 5.82 0 13s5.82 13 13 13\" clip-rule=\"evenodd\"/></g></svg></i></a>");


                content = content.replaceAll("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-tag\"></i></a>", "<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i><svg width=\"15\" height=\"15\" viewBox=\"0 0 16 16\"><path fill=\"#737373\" d=\"M2 1a1 1 0 0 0-1 1v4.586a1 1 0 0 0 .293.707l7 7a1 1 0 0 0 1.414 0l4.586-4.586a1 1 0 0 0 0-1.414l-7-7A1 1 0 0 0 6.586 1zm4 3.5a1.5 1.5 0 1 1-3 0a1.5 1.5 0 0 1 3 0\"/></svg></i></a>");
                content = content.replaceAll("<i class=\"fa fa-expand\"></i>", "<i><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"15\" height=\"15\" viewBox=\"0 0 16 16\"><path fill=\"#737373\" d=\"M11 2H2v9l1-1V3h7zM5 14h9V5l-1 1v7H6z\"/><path fill=\"#737373\" d=\"M16 0h-5l1.8 1.8l-4.5 4.5l1.4 1.4l4.5-4.5L16 5zM7.7 9.7L6.3 8.3l-4.5 4.5L0 11v5h5l-1.8-1.8z\"/></svg></i>");
                content = content.replaceAll("<i class=\"fa fa-compress\"></i>", "<i><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"15\" height=\"15\" viewBox=\"0 0 16 16\"><path fill=\"#737373\" d=\"M12 0H0v12l1-1V1h10zM4 16h12V4l-1 1v10H5z\"/><path fill=\"#737373\" d=\"M7 9H2l1.8 1.8L0 14.6L1.4 16l3.8-3.8L7 14zm9-7.6L14.6 0l-3.8 3.8L9 2v5h5l-1.8-1.8z\"/></svg></i>");

                content = content.replaceAll("<script src=\"https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@ce8b10435bcbae260c334c0d0c6b61d2c19b6168/spark/js/spark-script.js\"></script>", replaceContent2);

//                content = content.replaceAll("", "");



                Files.writeString(path, content, charset);
            } catch (IOException e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }

        private void logReportSteps() {
            if (!StepListener.threadedExecutedTestSteps.get().isEmpty()) {
                threadedCompleteStepDescriptionList.get().subList(0, StepListener.threadedExecutedTestSteps.get().size())
                        .clear();
            }
            if (!threadedCompleteStepDescriptionList.get().isEmpty()) {
                ExtentTest skippedNode = ExtentReportManager.getTest().createNode("Skipped Steps");
                for (String stepDescription : threadedCompleteStepDescriptionList.get()) {
                    skippedNode.skip(StringManipulation.cleanBDDActions(stepDescription));
                }
            }
            StepListener.threadedExecutedTestSteps.get().clear();
            threadedCompleteStepDescriptionList.get().clear();
        }

        private void logTestCategories() {
            if (!threadedTestCategories.get().isEmpty()) {
                for (String category : threadedTestCategories.get()) {
                    ExtentReportManager.getTest().assignCategory(category);
                }
            }
            threadedTestCategories.get().clear();
        }
    }