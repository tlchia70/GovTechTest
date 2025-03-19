package com.ahq.globals;

import com.ahq.addons.loc;
import com.ahq.globals.utilities.UtilPassword;
import com.qmetry.qaf.automation.step.QAFTestStep;
import org.openqa.selenium.ElementClickInterceptedException;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class actions {
    
    private final Set<String> elementTypes = Set.of("button", "link", "text", "checkbox", "radio-button", "select");
    
    /**
     * This function takes a formatted BDD step and breaks down the internal actions for a given page.
     * All the details required are passed as a single string and broken down into individual actions.
     *
     * @param page        - Page name the holds the elements for the given actions
     * @param userComment - A discarded, non-important comment to aid BDD readability
     * @param userActions - A string containing all required actions in the form of {action}{value}{element-name}
     * @throws Exception
     */
    @QAFTestStep(description = "{0}: {1} -action/s:{2}")
    public void masterCommand(String page, String userComment, String userActions) throws Exception {
        // Split the userActions string into individual actions
        String delim = ",";
        String regex = "(?<!\\\\)" + Pattern.quote(delim);
        String[] commands = userActions.split(regex);
        // Initialise the parameters for an action
        for (String command : commands) {
            String action = "", param_1 = "", param_2 = "", param_3 = "", param_4 = "", param_5 = "", param_6 = "";
            command = command.replace("\\,", ",");
            System.out.println("Command: " + command);
            Pattern regexPattern = Pattern.compile("\\{([^}]*)\\}");
            Matcher patternMatcher = regexPattern.matcher(command);
            int count = 0;
            // Assign all the parameters for a given action
            while (patternMatcher.find()) {
                count++;
                if (count == 1) {
                    action = patternMatcher.group(1).trim().toLowerCase();
                } else if (count == 2) {
                    param_1 = patternMatcher.group(1).trim();
                } else if (count == 3) {
                    param_2 = patternMatcher.group(1).trim();
                } else if (count == 4) {
                    param_3 = patternMatcher.group(1).trim();
                } else if (count == 5) {
                    param_4 = patternMatcher.group(1).trim();
                } else if (count == 6) {
                    param_5 = patternMatcher.group(1).trim();
                } else if (count == 7) {
                    param_6 = patternMatcher.group(1).trim();
                }
            }
            // Extra wait for all actions allows for animations to complete before the next action to avoid issues


            BrowserGlobal.iWaitForMilliseconds("500");



            // Perform appropriate action for the given action keywords
            switch (action) {
                case "click":
                    if (param_2.equalsIgnoreCase("input")) {
                        try {
                            BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "input", param_4));
                            BrowserGlobal.iClickAndFillInTo(param_3, loc.get(page, "input", param_4));
                        } catch (ElementClickInterceptedException e) {
                            // Try again if click is intercepted. Unknown issue with application.
                            BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "input", param_4));
                            BrowserGlobal.iClickAndFillInTo(param_3, loc.get(page, "input", param_4));
                        }
                    } else {
                        try {
                            performClickAction(page, action, param_1, param_2, param_3);
                        } catch (ElementClickInterceptedException e) {
                            // Try again if click is intercepted. Unknown issue with application.
                            performClickAction(page, action, param_1, param_2, param_3);
                        }
                    }
                    break;
                case "double-click":
                    performDoubleClick(page, param_1, param_2, param_3, param_4, param_5, param_6);
                    break;
                case "fill":
                case "input":
                    performInputFill(page, param_1, param_3);
                    break;
                case "open":
                    performOpenBrowser(param_1, param_2, param_3, param_4, param_5, param_6);
                    break;
                case "press":
                    performKeyPress(page, param_1, param_3);
                    break;
                case "select":
                    performSelectDropdown(page, param_1, param_2, param_3, param_4, param_5, param_6);
                    break;
                case "deselect":
                    performDeselectDropdown(page, param_1, param_2, param_3, param_4);
                    break;
                case "switch":
                    performFrameSwitch(page, param_2, param_4, param_5);
                    break;
                case "verify":
                    performVerifyAction(page, param_1, param_2, param_3, param_4, param_5);
                    break;
                case "wait":
                    performWaitAction(page, param_1, param_2, param_3, param_4, param_5, param_6);
                    break;
                default:
                    BrowserGlobal.iFailStepWithInfo("ACTION NOT DEFINED: Please check action: " + action);
                    break;
            }
        }
    }
    
    private void performInputFill(String page, String param_1, String param_3) throws Exception {
        BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "input", param_3));
        String inputText = param_1.replace("|", ",");
        BrowserGlobal.iFillInTo(UtilPassword.check(inputText), loc.get(page, "input", param_3));
    }
    
    private void performWaitAction(String page, String param_1, String param_2, String param_3, String param_4, String param_5, String param_6) throws Exception {
        if (param_1.equalsIgnoreCase("for") && param_5.equalsIgnoreCase("visible")) {
            BrowserGlobal.iWaitUntilElementVisible(loc.get(page, param_2, param_6));
        } else if (param_1.equalsIgnoreCase("for") && param_5.equalsIgnoreCase("hidden")) {
            BrowserGlobal.iWaitUntilElementNotVisible(loc.get(page, param_2, param_6));
        } else if (param_2.isBlank()) {
            BrowserGlobal.iWaitForSeconds(param_1);
        } else {
            BrowserGlobal.iFailStepWithInfo("Undefined parameters for 'WAIT' action");
        }
    }
    
    private void performDoubleClick(String page, String param_1, String param_2, String param_3, String param_4, String param_5, String param_6) throws Exception {
        if (param_1.equalsIgnoreCase("wait")) {
            BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "input", param_6));
            BrowserGlobal.iDoubleClickWaitAndFillInTo(param_5, loc.get(page, "input", param_6), param_2);
        } else if (param_2.equalsIgnoreCase("input")) {
            BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "input", param_4));
            BrowserGlobal.iDoubleClickAndFillInTo(param_3, loc.get(page, "input", param_4));
        } else {
            BrowserGlobal.iFailStepWithInfo("Specific 'double-click' action not defined");
        }
    }
    
    private void performSelectDropdown(String page, String param_1, String param_2, String param_3, String param_4, String param_5, String param_6) throws Exception {
        if (param_1.equalsIgnoreCase("text")) {
            BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "select", param_4));
            BrowserGlobal.iSelectDropdownWithText(loc.get(page, "select", param_4), param_2);
        } else {
            BrowserGlobal.iFailStepWithInfo("Undefined parameter for 'SELECT' action: " + param_1);
        }
    }
    
    private void performDeselectDropdown(String page, String param_1, String param_2, String param_3, String param_4) throws Exception {
        switch (param_1) {
            case "all":
                BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "select", param_3));
                BrowserGlobal.iDeselectAllInDropdown(loc.get(page, "select", param_3));
                break;
            case "index":
                BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "select", param_4));
                BrowserGlobal.iDeselectDropdownWithIndex(loc.get(page, "select", param_4), param_2);
                break;
            case "value":
                BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "select", param_4));
                BrowserGlobal.iDeselectDropdownWithValue(loc.get(page, "select", param_4), param_2);
                break;
            case "text":
                BrowserGlobal.iWaitUntilElementVisible(loc.get(page, "select", param_4));
                BrowserGlobal.iDeselectDropdownWithText(loc.get(page, "select", param_4), param_2);
                break;
            default:
                BrowserGlobal.iFailStepWithInfo("Undefined parameter for 'DESELECT' action: " + param_1);
                break;
        }
    }
    
    private void performFrameSwitch(String page, String param_2, String param_4, String param_5) throws Exception {
        if (param_2.equalsIgnoreCase("iframes")) {
            BrowserGlobal.iWaitForMilliseconds("500");
            switch (param_4) {
                case "index":
                    BrowserGlobal.iSwitchToIFrameByIndex(param_5);
                    break;
                case "name":
                case "id":
                    BrowserGlobal.iSwitchToIFrameByIdOrName(param_5);
                    break;
                case "title":
                    BrowserGlobal.iSwitchToIFrameByTitle(param_5);
                    break;
                case "locator":
                    BrowserGlobal.iSwitchToIFrameByLocator(loc.get(page, "iframe", param_5));
                    break;
                case "parent-window":
                    BrowserGlobal.iSwitchToParentWindowFrame();
                    break;
                default:
                    BrowserGlobal.iFailStepWithInfo("Undefined parameter for 'SWITCH' action: " + param_4);
                    break;
            }
        }
    }
    
    private void performKeyPress(String page, String param_1, String param_3) throws Exception {
        if (param_1.equalsIgnoreCase("enter")) {
            BrowserGlobal.iPressReturnOrEnterKeyIn(loc.get(page, "input", param_3));
        } else {
            BrowserGlobal.iFailStepWithInfo("Undefined parameter for 'PRESS' action: " + param_1);
        }
    }
    
    private void performOpenBrowser(String param_2, String param_3, String param_4, String param_5, String param_6, String param_7) throws Exception {
        // {open}{browser}{url}{width}{width_value}{height}{height_value}
        if (param_2.equalsIgnoreCase("browser") && param_4.equalsIgnoreCase("width") && param_6.equalsIgnoreCase("height")) {
//            BrowserGlobal.iOpenWebBrowserWindowSize(param_3, param_5, param_7);
            BrowserGlobal.iOpenWebBrowserWithWindowSize(param_3, param_5, param_7);

        }
        // {open}{browser}{url}{maximise}{screenshot}
        else if (param_2.equalsIgnoreCase("browser") && param_4.equalsIgnoreCase("maximise") && param_5.equalsIgnoreCase("screenshot")) {
//            BrowserGlobal.iOpenWebBrowserMaximiseAndScreenshot(param_3);
            BrowserGlobal.iOpenWebBrowserMaximiseWindowAndTakeScreenshot(param_3);
        }
        // {open}{browser}{url}
        else if (param_2.equalsIgnoreCase("browser")) {
            BrowserGlobal.iOpenWebBrowser(param_3);
        } else {
            BrowserGlobal.iFailStepWithInfo("Undefined parameters for 'open' action");
        }
    }
    
    private void performClickAction(String page, String action, String param_1, String param_2, String param_3) throws Exception {
        if ((param_2.equalsIgnoreCase("checkbox") || param_2.equalsIgnoreCase("radio-button")) && param_1.contains(":")) {
            String[] param_2_split = param_1.split(":");
            String[] checkboxValues = param_2_split[1].split(";");
            for (String checkboxValue : checkboxValues) {
                BrowserGlobal.iWaitForMilliseconds("100");
                if (param_3.equalsIgnoreCase("if-present")) {
                    BrowserGlobal.iClickOnLocIfPresent(loc.get(page, param_2, param_2_split[0] + " " + checkboxValue));
                } else if (param_3.equalsIgnoreCase("once-enabled")) {
                    BrowserGlobal.iClickOnLocOnceEnabled(loc.get(page, param_2, param_2_split[0] + " " + checkboxValue));
                } else {
//                    BrowserGlobal.iClickOnElementIfNotSelected(loc.get(page, param_2, param_2_split[0] + " " + checkboxValue));
                    BrowserGlobal.iClickOnCheckboxOrRadioIfNotSelected(loc.get(page, param_2, param_2_split[0] + " " + checkboxValue));

                }
            }
        } else if (elementTypes.contains(param_2.toLowerCase()) && param_3.equalsIgnoreCase("if-present")) {
            BrowserGlobal.iClickOnLocIfPresent(loc.get(page, param_2, param_1));
        } else if (elementTypes.contains(param_2.toLowerCase()) && param_3.equalsIgnoreCase("once-enabled")) {
            BrowserGlobal.iClickOnLocOnceEnabled(loc.get(page, param_2, param_1));
        } else if (elementTypes.contains(param_2.toLowerCase())) {
            BrowserGlobal.iScrollToAnElementAndClick(loc.get(page, param_2, param_1));
        } else {
            BrowserGlobal.iFailStepWithInfo("!! ACTION NOT DEFINED !!: Please check 'click' action: " + page + " " + action + " " + param_1 + " " + param_2 + " ");
        }
    }
//    {verify}{text}{<1:full-text:1>}{present}{in}{page}
    private void performVerifyAction(String page, String param_1, String param_2, String param_3, String param_4, String param_5) throws Exception {
        if (param_1.equalsIgnoreCase("link") &&
            param_2.equalsIgnoreCase("text") &&
            param_3.equalsIgnoreCase("is")) {
            BrowserGlobal.iAssertLinkWithTextPresent(param_4);
        } else if (param_1.equalsIgnoreCase("text") &&
                param_3.equalsIgnoreCase("present") &&
                param_4.equalsIgnoreCase("in") &&
                param_5.equalsIgnoreCase("page")) {
            BrowserGlobal.iAssertTextPresentInPage(param_2);
        } else if (param_1.equalsIgnoreCase("link") &&
                   param_2.equalsIgnoreCase("text") &&
                   param_3.equalsIgnoreCase("contains")) {
            BrowserGlobal.iAssertLinkWithPartialTextPresent(param_4);
        } else if (param_1.equalsIgnoreCase("title") &&
                   param_2.equalsIgnoreCase("text") &&
                   param_3.equalsIgnoreCase("is")) {
            BrowserGlobal.iAssertTitleText(param_4);
        } else if (param_1.equalsIgnoreCase("title") &&
                   param_2.equalsIgnoreCase("text") &&
                   param_3.equalsIgnoreCase("contains")) {
            BrowserGlobal.iAssertTitlePartialText(param_4);
        } else if (param_1.equalsIgnoreCase("text") &&
                   param_2.equalsIgnoreCase("present") &&
                   param_3.equalsIgnoreCase("on")) {
            BrowserGlobal.iAssertTextPresentInPage(param_5);
        } else if (param_1.equalsIgnoreCase("checkbox") &&
                   param_3.equalsIgnoreCase("is") &&
                   param_4.equalsIgnoreCase("checked")) {
            BrowserGlobal.iScrollToAnElement(loc.get(page, "checkbox", param_2));
            BrowserGlobal.iAssertElementSelected(loc.get(page, "checkbox", param_2));
        } else if (elementTypes.contains(param_2.toLowerCase()) &&
                   param_3.equalsIgnoreCase("is-present")) {
            BrowserGlobal.iAssertElementPresent(loc.get(page, param_2, param_1));
        } else if (elementTypes.contains(param_2.toLowerCase()) &&
                   param_3.equalsIgnoreCase("is-not-present")) {
            BrowserGlobal.iAssertElementPresent(loc.get(page, param_2, param_1));
        } else if (elementTypes.contains(param_2.toLowerCase()) &&
                   param_3.equalsIgnoreCase("is-visible")) {
            BrowserGlobal.iAssertElementPresent(loc.get(page, param_2, param_1));
        } else if (elementTypes.contains(param_2.toLowerCase()) &&
                   param_3.equalsIgnoreCase("is-not-visible")) {
            BrowserGlobal.iAssertElementPresent(loc.get(page, param_2, param_1));
        } else if (elementTypes.contains(param_2.toLowerCase()) &&
                   param_3.equalsIgnoreCase("contains")) {
            BrowserGlobal.iScrollToAnElement(loc.get(page, param_2, param_1));
            if (param_4.contains("|")) {
                String[] verificationStrings = param_4.split("\\|");
                for (String text : verificationStrings) {
                    BrowserGlobal.iVerifyLocatorWithPartialTextIgnoringCase(loc.get(page, param_2, param_1), text.trim());
                }
            } else {
                BrowserGlobal.iVerifyLocatorWithPartialTextIgnoringCase(loc.get(page, param_2, param_1), param_4);
            }
        } else if (elementTypes.contains(param_2.toLowerCase()) &&
                   param_3.equalsIgnoreCase("html")) {
            BrowserGlobal.iScrollToAnElement(loc.get(page, param_2, param_1));
            if (param_5.contains("|")) {
                String[] verificationStrings = param_5.split("\\|");
                for (String text : verificationStrings) {
                    BrowserGlobal.iVerifyElementInnerHtmlContains(loc.get(page, param_2, param_1), text.trim());
                }
            } else {
                BrowserGlobal.iVerifyElementInnerHtmlContains(loc.get(page, param_2, param_1), param_5);
            }
        } else {
            BrowserGlobal.iFailStepWithInfo("!! ACTION NOT DEFINED !!: Please check 'verify' action: " + page + " " + param_1 + " " + param_2 + param_3 + " " + param_4);
        }
    }
}
