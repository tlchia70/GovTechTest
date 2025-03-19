package com.ahq.globals;

import com.ahq.addons.patternLoc;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriver;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import static com.qmetry.qaf.automation.step.CommonStep.waitForPresent;

public class web {

//
//    /**
//     * @param url [Open browser with URL]
//     */
//    @QAFTestStep(description = "Web: Open url {0}")
//    @And("Web: Open url {string}")
//    public static void openBrowser(String url) throws Exception {
//        BrowserGlobal.iOpenWebBrowser(url);
//        web.setCurrentExecDateTime_Web();
//    }
//    /**
//     * @param url [Open browser with URL]
//     */
//    @QAFTestStep(description = "Web: Open url {0} And Maximize")
//    @And("Web: Open url {string} And Maximize")
//    public static void openMaximizedBrowser_Web(String url) throws Exception {
//        BrowserGlobal.iOpenWebBrowserAndMaximize(url);
//        setCurrentExecDateTime_Web();
//    }
////    /**
////     * @param field [Field name]
////     */
////    @QAFTestStep(description = "Web: Click Link {0}")
////    @And("Web: Click Link {string}")
////    public static void clickLink_Web(String field) throws Exception {
////        BrowserGlobal.iWaitUntilElementPresent(patternLoc.link(getPageName(),field));
////        BrowserGlobal.iScrollToAnElement(patternLoc.link(getPageName(),field));
////        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.link(getPageName(),field));
////        BrowserGlobal.iClickOn(patternLoc.link(getPageName(),field));
////    }
//
//
//
//    /**
//     * @param field [Field name]
//     */
//    @QAFTestStep(description = "Web: Click Switch Button {0}")
//    @And("Web: Click Switch Button {string}")
//    public static void clickSwitchButton_Web(String field) throws Exception {
//        BrowserGlobal.iScrollToAnElement(patternLoc.switchButton(getPageName(), field));
//        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.switchButton(getPageName(), field));
//        BrowserGlobal.iClickOn(patternLoc.switchButton(getPageName(), field));
//    }
//
//    /**
//     * @param text [text to fill]
//     * @param field [Field name]
//     */
//    @QAFTestStep(description = "Web: Input {0} into {1}")
//    @And("Web: Input {string} into {string}")
//    public static void input_Web(String text,String field) throws Exception {
//        BrowserGlobal.iWaitUntilElementPresent(patternLoc.input(getPageName(),field));
//        BrowserGlobal.iScrollToAnElement(patternLoc.input(getPageName(),field));
//        BrowserGlobal.iWaitUntilElementVisible(patternLoc.input(getPageName(),field));
//        BrowserGlobal.iInputInTo(text, patternLoc.input(getPageName(),field));
//    }
//
//    /**
//     * @param text [text to fill]
//     * @param placeholder_text [Placeholder Text]
//     */
//    @QAFTestStep(description = "Web: Input {0} into {1} with Placeholder")
//    @And("Web: Input {string} into {string} with Placeholder")
//    public static void inputWithPlaceholder_Web(String text,String placeholder_text) throws Exception {
//        BrowserGlobal.iWaitUntilElementPresent(patternLoc.input(getPageName(),placeholder_text,Boolean.FALSE));
//        BrowserGlobal.iScrollToAnElement(patternLoc.input(getPageName(),placeholder_text,Boolean.FALSE));
//        BrowserGlobal.iWaitUntilElementVisible(patternLoc.input(getPageName(),placeholder_text,Boolean.FALSE));
//        BrowserGlobal.iInputInTo(text, patternLoc.input(getPageName(),placeholder_text,Boolean.FALSE));
//    }
//
//    /**
//     * @param text [text to fill]
//     * @param input_name [Input field Name]
//     */
//    @QAFTestStep(description = "Web: Input {0} into {1} without Label")
//    @And("Web: Input {string} into {string} without Label")
//    public static void inputWithoutLabel_Web(String text,String input_name) throws Exception {
//        BrowserGlobal.iWaitUntilElementPresent(patternLoc.input(getPageName(),input_name,Boolean.FALSE));
//        BrowserGlobal.iScrollToAnElement(patternLoc.input(getPageName(),input_name,Boolean.FALSE));
//        BrowserGlobal.iWaitUntilElementVisible(patternLoc.input(getPageName(),input_name,Boolean.FALSE));
//        BrowserGlobal.iInputInTo(text, patternLoc.input(getPageName(),input_name,Boolean.FALSE));
//    }
//
//    /**
//     *
//     * @param field [Field name]
//     */
//    @QAFTestStep(description = "Web: Click Checkbox with text {1}")
//    @And("Web: Click Checkbox with text {string}")
//    public static void clickCheckBox_Web(String field) throws Exception {
//        BrowserGlobal.iWaitUntilElementPresent(patternLoc.checkbox(getPageName(),field));
//        BrowserGlobal.iScrollToAnElement(patternLoc.checkbox(getPageName(),field));
//        BrowserGlobal.iWaitUntilElementVisible(patternLoc.checkbox(getPageName(),field));
//        BrowserGlobal.iClickOn(patternLoc.checkbox(getPageName(),field));
//    }
//
//    /**
//     * @param dropdown_Text [Text to be selected in dropdown]
//     * @param field [Field name]
//     */
//    @QAFTestStep(description = "Web: Select Dropdown with text {0} in {1}")
//    @And("Web: Select Dropdown with text {string} in {string}")
//    public static void selectDropdownByText_Web(String dropdown_Text, String field) throws Exception {
//        BrowserGlobal.iWaitUntilElementPresent(patternLoc.select(getPageName(), field));
//        BrowserGlobal.iScrollToAnElement(patternLoc.select(getPageName(), field));
//        BrowserGlobal.iSelectDropdownWithText(patternLoc.select(getPageName(), field), dropdown_Text);
//    }
//
//    /**
//     * @param dropdown_Text [Text to be selected in dropdown]
//     * @param field [Field name]
//     */
//    @QAFTestStep(description = "Web: Select dropdown by input text {0} in {1}")
//    @And("Web: Select dropdown by input text {string} in {string}")
//    public static void selectDropdownByInput_Web(String dropdown_Text, String field) throws Exception {
//        BrowserGlobal.iWaitUntilElementPresent(patternLoc.input(getPageName(), field));
//        BrowserGlobal.iScrollToAnElement(patternLoc.input(getPageName(), field));
//        BrowserGlobal.iFillInTo(dropdown_Text, patternLoc.input(getPageName(), field));
//        BrowserGlobal.iWaitForMilliseconds("500");
//        BrowserGlobal.iPressKey("Enter");
//    }
//
//    /**
//     * @param radio_Text [Text to be selected in Radio button]
//     * @param field [Field name]
//     */
//
//    @QAFTestStep(description = "Web: Click Radio Button with text {0} in {1}")
//    @And("Web: Click Radio Button with text {string} in {string}")
//    public static void clickRadioButton_Web(String radio_Text, String field) throws Exception {
//        BrowserGlobal.iWaitUntilElementPresent(patternLoc.radioButton(getPageName(),field,radio_Text));
//        BrowserGlobal.iScrollToAnElement(patternLoc.radioButton(getPageName(),field,radio_Text));
//        BrowserGlobal.iClickOn(patternLoc.radioButton(getPageName(),field,radio_Text));
//    }
//
//
//
//
//
////    /**
////     * @param page [Page name]
////     * @param header_text [Header text to be verified]
////     */
////    @QAFTestStep(description = "Web: Verify page header text {1}")
////    @And("Web: Verify page header text {string}")
////    public static void verifyPageHeader(String header_text) throws Exception {
////        BrowserGlobal.iWaitForPageToLoad();
////        BrowserGlobal.iWaitUntilElementPresent(patternLoc.header(getPageName(),header_text));
////        BrowserGlobal.iScrollToAnElement(patternLoc.header(getPageName(),header_text));
////        BrowserGlobal.iWaitUntilElementVisible(patternLoc.header(getPageName(),header_text));
////        BrowserGlobal.iAssertElementText(patternLoc.header(getPageName(),header_text),header_text);
////    }
//    /**
//     * @param text [text to be verified in page]
//     */
//    @QAFTestStep(description = "Web: Verify page contains Text {0}")
//    @And("Web: Verify page contains Text {string}")
//    public static void verifyPageContainsText_Web(String text) throws Exception {
//        BrowserGlobal.iWaitForPageToLoad();
//        BrowserGlobal.iAssertTextPresentInPage(text);
//    }
//
//    /**
//     * @throws Exception
//     */
//    @QAFTestStep(description = "Web: Wait for page to Load")
//    @And("Web: Wait for page to Load")
//    public static void waitForPageToLoad_Web() throws Exception {
//        BrowserGlobal.iWaitForPageToLoad();
//    }
//    /**
//     *
//     * @param tabNumber [Tab number to locate]
//     *
//     */
//    @QAFTestStep(description = "Web: Move to Browser Tab {0}")
//    @And("Web: Move to Browser Tab {string}")
//    public static void moveToBrowserTab(String tabNumber) throws Exception {
//        BrowserGlobal.iSwitchWindowByIndex(tabNumber);
//    }
//
//
////    /**
////     *
////     * @param field [Field name]
////     *
////     */
////    @QAFTestStep(description = "Web: Choose Field:{0} Value:{1}")
////    public static void tabOut( String field) throws Exception {
////        BrowserGlobal.iClickAndTabInToThenEnter(patternLoc.input(getPageName(),field));
////    }
///**
// *
// * @param field [Field name]
// *
// */
///* @QAFTestStep(description = "tab Out and Enter on Field {0}")
//public static void tabOnceAndEnter( String field) throws Exception {
//BrowserGlobal.iTabInToThenEnter(patternLoc.input(getPageName(),field));
//} */
////    /**
////     * @param text [text to fill]
////     * @param field [Field name]
////     */
////    @QAFTestStep(description = "Web: Input {0} into {1} without label")
////    public static void inputWithoutLabel(String text,String field) throws Exception {
////        BrowserGlobal.iWaitUntilElementPresent(patternLoc.input(getPageName(),field, false));
////        BrowserGlobal.iScrollToAnElement(patternLoc.input(getPageName(),field, false));
////        BrowserGlobal.iWaitUntilElementVisible(patternLoc.input(getPageName(),field, false));
////        BrowserGlobal.iInputInTo(text, patternLoc.input(getPageName(),field, false));
////    }
//
///**
// * @param field [Field name]
// */
///* @QAFTestStep(description = "Click button {0}")
//public static void clickModalButton(String field) throws Exception {
//BrowserGlobal.iWaitUntilElementVisibleWithTimeout(patternLoc.modalButton(getPageName(),field),"10");
//BrowserGlobal.iScrollToAnElement(patternLoc.modalButton(getPageName(),field));
//BrowserGlobal.iWaitUntilElementEnabled(patternLoc.modalButton(getPageName(),field));
//BrowserGlobal.iClickOn(patternLoc.modalButton(getPageName(),field));
//} */
////    /**
////     * @param field [Field name]
////     */
////    @QAFTestStep(description = "Web: Click and Input field {0}")
////    public static void clickInput(String field) throws Exception {
////        BrowserGlobal.iClickOn(patternLoc.input(getPageName(),field));
////    }
//
//    /**
//     * @param link_text [Link Text to click]
//     */
//    @QAFTestStep(description = "Web: Double click on link {0}")
//    @And("Web: Double click on link {string}")
//    public static void doubleClickLink_Web(String link_text) throws Exception {
//        BrowserGlobal.iScrollToAnElement(patternLoc.link(getPageName(),link_text));
//        BrowserGlobal.iWaitUntilElementVisible(patternLoc.link(getPageName(),link_text));
//        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.link(getPageName(),link_text));
//        BrowserGlobal.iDoubleClickOn(patternLoc.link(getPageName(),link_text));
//    }
//
//
//    /**
//     *
//     *
//     */
//    @QAFTestStep(description = "Web: Take screenshot")
//    @And("Web: Take screenshot")
//    public static void takeScreenshot_Web() throws Exception {
//        BrowserGlobal.iTakeScreenshot();
//    }
//
//    /**
//     *
//     * @param iframeIdOrName- [iFrame id or name]
//     *
//     */
//    @QAFTestStep(description="Web: Move to iframe by Id or Name {0}")
//    @And("Web: Move to iframe by Id or Name {string}")
//    public static void moveToIframeByIdOrName_Web(String iframeIdOrName) throws Exception {
//        BrowserGlobal.iWaitForPageToLoad();
//        BrowserGlobal.iSwitchToIFrameByIdOrName(iframeIdOrName);
//    }
////    /**
////     * @param field [Field name]
////     */
////    @QAFTestStep(description = "Web: Move to Subheader {0}")
////    public static void clickSubHeader(String field) throws Exception {
////        BrowserGlobal.iScrollToAnElement(patternLoc.subHeader(getPageName(),field));
////        BrowserGlobal.iWaitUntilElementVisible(patternLoc.subHeader(getPageName(),field));
////        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.subHeader(getPageName(),field));
////        BrowserGlobal.iClickOn(patternLoc.subHeader(getPageName(),field));
////    }
//
////    /**
////     *
////     * @param field [Field name]
////     */
////    @QAFTestStep(description = "Web: Click select field with value Value:{1}")
////    public static void clickSelect(String field) throws Exception {
////        BrowserGlobal.iWaitUntilElementPresent(patternLoc.select(getPageName(),field));
////        BrowserGlobal.iScrollToAnElement(patternLoc.select(getPageName(),field));
////        BrowserGlobal.iClickOn(patternLoc.select(getPageName(),field));
////    }
//
////    /**
////     * @param field [Field name]
////     */
////    @QAFTestStep(description = "Click Graph link {0}")
////    public static void clickGraphLink(String field) throws Exception {
////// BrowserGlobal.iScrollToAnElement(patternLoc.link(getPageName(),field));
////        BrowserGlobal.iWaitUntilElementPresent(patternLoc.graphlink(getPageName(),field));
////        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.graphlink(getPageName(),field));
////        BrowserGlobal.iClickOn(patternLoc.graphlink(getPageName(),field));
////    }
//

    /**
     * @return [Page Name]
     */
    @QAFTestStep(description = "Web: Get stored page name")
    @And("Web: Get stored page name")
    public static String getPageName() {
        return getBundle().getProperty("auto.page.name").toString();
    }


    // NEW WEB GLOBALS

    /**
     * @param pageName [Set page name for Locator Name Generation]
     */
    @QAFTestStep(description = "Web: Set-Page-Name Value:{0}")
    @And("Web: Set-Page-Name Value:{string}")
    public static void setPageName_Web(String pageName) throws Exception {
        getBundle().setProperty("auto.page.name", pageName);
    }

    /**
     * @param locationName [Set Location name of the field within Page]
     */
    @QAFTestStep(description = "Web: Set-Field-Location Value:{0}")
    @And("Web: Set-Field-Location Value:{string}")
    public static void setFieldLocation_Web(String locationName) throws Exception {
        String pageName = getBundle().getPropertyValue("auto.page.name");
        if (pageName.contains("::")) {
            String[] fldNameSplit = pageName.trim().split("::");
            getBundle().setProperty("auto.page.name", fldNameSplit[0].trim() + "::" + locationName.trim());
        } else {
            getBundle().setProperty("auto.page.name", pageName + "::" + locationName.trim());
        }
    }

    /**
     * Setting Current execution time to variable - var.exec.dateTime
     */
    @QAFTestStep(description = "Web: Set-Current-Execution-Date-Time")
    @And("Web: Set-Current-Execution-Date-Time")
    public static void setCurrentExecDateTime_Web() throws Exception {
        getBundle().setProperty("exec.web.dateTime", Utils.dateTime_currentDateTime());
    }

    /**
     *
     */
    @QAFTestStep(description = "Web: Remove-Field-Location")
    @And("Web: Remove-Field-Location")
    public static void removeFieldLocation_Web() throws Exception {
        String pageName = getBundle().getPropertyValue("auto.page.name");
        if (pageName.contains("::")) {
            String[] fldNameSplit = pageName.trim().split("::");
            getBundle().setProperty("auto.page.name", fldNameSplit[0].trim());
        }
    }


    /**
     * Getting execution time from variable - var.exec.dateTime
     */
    @QAFTestStep(description = "Web: Get-Execution-Date-Time")
    @And("Web: Get-Execution-Date-Time")
    public static String getExecutionDateTime_Web() throws Exception {
        return getBundle().getPropertyValue("exec.web.dateTime");
    }

    /**
     * @param url [URL to Open]
     */
    @QAFTestStep(description = "Web: Open-Browser Url:{0}")
    @And("Web: Open-Browser Url:{string}")
    public static void openBrowser_Web(String url) throws Exception {
        BrowserGlobal.iOpenWebBrowser(url);
        web.setCurrentExecDateTime_Web();
        web.setPageName_Web("NO Page Name Set");
    }

    /**
     * @param url [URL to Open]
     */
    @QAFTestStep(description = "Web: Open-Browser-And-Maximise Url:{0}")
    @And("Web: Open-Browser-And-Maximise Url:{string}")
    public static void openBrowserAndMaximise_Web(String url) throws Exception {
        BrowserGlobal.iOpenWebBrowserAndMaximize(url);
        web.setCurrentExecDateTime_Web();
        web.setPageName_Web(web.getPageName());
    }

    /**
     * @param window_width  [Browser Window width]
     * @param window_height [Browser Window Height
     * @param url           [URL to Open]
     */
    @QAFTestStep(description = "Web: Open-Browser-With-Set-Window-Size Width:{1} Height:{2} Url:{0} ")
    @And("Web: Open-Browser-With-Set-Window-Size Width:{string} Height:{string} Url:{string} ")
    public static void openBrowserWithSetWindowSize_Web(String window_width, String window_height, String url) throws Exception {
        BrowserGlobal.iOpenWebBrowserWithWindowSize(url, window_width, window_height);
        web.setCurrentExecDateTime_Web();
        web.setPageName_Web("NO Page Name Set");
    }

    // WAIT

    /**
     * @param seconds [Wait time in Seconds]
     */
    @QAFTestStep(description = "Web: Wait-For-Seconds Value:{0}")
    @And("Web: Wait-For-Seconds Value:{string}")
    public static void waitForSecs_Web(String seconds) throws Exception {
        BrowserGlobal.iWaitForSeconds(seconds);
    }

    /**
     * @param milliseconds [Wait time in Milli Secs]
     */
    @QAFTestStep(description = "Web: Wait-For-Milliseconds Value:{0}")
    @And("Web: Wait-For-Milliseconds Value:{string}")
    public static void waitForMilliseconds(String milliseconds) throws Exception {
        BrowserGlobal.iWaitForMilliseconds(milliseconds);
    }

// VERIFY
//    Web: Verify Page-Title Value:{0} Page-Name:{1}
//    Web: Verify Page-Title-Partial Value:{0} Page-Name:{1}
//    Web: Verify Page-Contains-Text Value:{0} Page-Name:{1}
//    Web: Verify Page-Header-Text Field:{0} Page-Name:{1}
//    Web: Verify Page-Sub-Header-Text Field:{0} Page-Name:{1}

    /**
     * @param header_text [Header text to be verified]
     */
    @QAFTestStep(description = "Web: Verify-Page-Header-Text Field:{0}")
    @And("Web: Verify Page-Header-Text Field:{string}")
    public static void verifyPageHeaderText_Web(String header_text) throws Exception {
        BrowserGlobal.iAssertElementText(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.header(getPageName(), header_text)), header_text);
    }

    /**
     * @param header_text [Header text to be verified]
     */
    @QAFTestStep(description = "Web: Scroll to Page-Header-Text Field:{0}")
    @And("Web: Scroll to Page-Header-Text Field:{string}")
    public static void scrollToPageHeaderText_Web(String header_text) throws Exception {
        BrowserGlobal.iScrollToAnElement(patternLoc.header(getPageName(), header_text));
    }

    /**
     * @param header_text [Header text to be verified]
     * @param page_name   [Page Name to be set]
     */
    @QAFTestStep(description = "Web: Wait-And-Verify Page-Header-Text Field:{0} Page-Name:{1}")
    @And("Web: Wait-And-Verify Page-Header-Text Field:{string} Page-Name:{string}")
    public static void waitAndVerifyPageHeaderTextWithPageName_Web(String header_text, String page_name) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        web.setPageName_Web(page_name);
        BrowserGlobal.iAssertElementText(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.header(getPageName(), header_text)), header_text);
    }

    @QAFTestStep(description = "Web: Click-Button Field:{0}")
    @And("Web: Click-Button Field:{string}")
    public static void clickButton_Web(String field_button_name) throws Exception {
        BrowserGlobal.iClickOn(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.button(getPageName(), field_button_name)));
    }

    @QAFTestStep(description = "Web: Click-Button no scroll Field:{0}")
    @And("Web: Click-Button no scroll Field:{string}")
    public static void clickButtonNoScroll_Web(String field_button_name) throws Exception {
        BrowserGlobal.iClickOn(waitFieldToBePresentAndEnabled(patternLoc.button(getPageName(), field_button_name)));
    }

    @QAFTestStep(description = "Web: Click-dropdown Field:{0}")
    @And("Web: Click-dropdown Field:{string}")
    public static void clickDropdown_Web(String field_dropdown_label) throws Exception {
        BrowserGlobal.iClickOn(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.dropdown(getPageName(), field_dropdown_label)));
    }

    @QAFTestStep(description = "Web: Click-List Field:{0}")
    @And("Web: Click-List Field:{string}")
    public static void clickList_Web(String field_List_name) throws Exception {
        BrowserGlobal.iClickOn(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.list(getPageName(), field_List_name)));
    }

    /**
     * @param title_text [Title text to be verified]
     * @param page_name  [Page Name to be set]
     */
    @QAFTestStep(description = "Web: Wait-And-Verify Page-Title-Text Title:{0} Page-Name:{1}")
    @And("Web: Wait-And-Verify Page-Title-Text Title:{string} Page-Name:{string}")
    public static void waitAndVerifyPageTitleTextWithPageName_Web(String title_text, String page_name) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        web.setPageName_Web(page_name);
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iAssertTitlePartialText(title_text);
    }

    /**
     * @param title_text [Page Title of the page]
     */
    @QAFTestStep(description = "Web: Verify page title {0}")
    @And("Web: Verify page title {string}")
    public static void verifyPageTitle_Web(String title_text) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        getBundle().setProperty("auto.page.name", getPageName());
        BrowserGlobal.iAssertTitleText(title_text);
    }


// FIELD

    /**
     * @param field_link_name [Link name to click]
     */
    @QAFTestStep(description = "Web: Click-Link Field:{0}")
    @And("Web: Click-Link Field:{string}")
    public static void clickLink_Web(String field_link_name) throws Exception {
        BrowserGlobal.iClickOn(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.link(getPageName(), field_link_name)));
    }


    /**
     * @param input_value [Input value to fill]
     * @param field_input [Input field name to fill]
     */
    @QAFTestStep(description = "Web: Input-Text Value:{0} Field:{1}")
    @And("Web: Input-Text Value:{string} Field:{string}")
    public static void inputText_Web(String input_value, String field_input) throws Exception {
        BrowserGlobal.iClearAndFillInTo(input_value, waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.input(getPageName(), field_input)));

    }


    /**
     * Input text/value to a given field
     * : I input {value} into {locator}
     *
     * @param input_value [Value to enter/input in a field]
     * @param field_input [Locator of the field]
     */
    @QAFTestStep(description = "I input search {value} into {locator}")
    @And("I input search {string} into {string}")
    public static void iInputSearch_web(String input_value, String field_input) throws Exception {
        BrowserGlobal.iInputSearch(input_value, waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.input(getPageName(), field_input)));
    }

//    Web: Input-Text-With-Placeholder Value:{0} Field:{1}

//    /**
//     * @param input_value [Input value to fill]
//     * @param field_input [Input field name to fill]
//     */
//    @QAFTestStep(description = "Web: Input-Text-With-Placeholder Value:{0} Field:{1}")
//    @And("Web: Input-Text-With-Placeholder Value:{string} Field:{string}")
//    public static void inputTextWithPlaceholderOrNoLabel_Web(String input_value, String field_input) throws Exception {
//        BrowserGlobal.iFillInTo(input_value, waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.input(getPageName(), field_input, false)));
//    }


    /**
     * @param locName [Field to be waited for PResent, Scroll & Enabled]
     */
//    private static String getLocAndwaitForFieldToBePresentScrollToCenterViewAndEnabled(String fieldType, String fieldName, String fieldValue,Boolean ignoreLabel) throws Exception {
//        String locName = patternLoc.getLocator(getPageName(),fieldType,fieldName,fieldValue,ignoreLabel);
//        BrowserGlobal.iWaitUntilElementPresent(locName);
//        BrowserGlobal.iScrollToAnElement(locName);
//        BrowserGlobal.iWaitUntilElementEnabled(locName);
//        return locName;
//    }
    private static String waitFieldToBePresentScrollAndEnabled(String locName) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(locName);
        BrowserGlobal.iScrollToAnElement(locName);
        BrowserGlobal.iWaitUntilElementEnabled(locName);
        return locName;
    }

    public static String waitForFieldToBePresentScrollToCenterViewAndEnabled(String locName) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(locName);
        BrowserGlobal.iScrollToAnElementAndAlignItInTheCenter(locName);
        BrowserGlobal.iWaitUntilElementEnabled(locName);

        return locName;
    }
    
    private static String waitFieldToBePresentAndEnabled(String locName) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(locName);
        BrowserGlobal.iWaitUntilElementEnabled(locName);
        return locName;
    }

    /**
     * @param field_button_name [Field name]
     */
    @QAFTestStep(description = "Web: Mouseover-Button Field {0}")
    @And("Web: Mouseover-Button Field {string}")
    public static void mouseoverButton_Web(String field_button_name) throws Exception {
        BrowserGlobal.iMouseoverOn(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.button(getPageName(), field_button_name)));
    }

    /**
     * @param field_button_name [Field name]
     */
    @QAFTestStep(description = "Web: Mouseover-Div Field {0}")
    @And("Web: Mouseover-Div Field {string}")
    public static void mouseoverDiv_Web(String field_button_name) throws Exception {
        BrowserGlobal.iMouseoverOn(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.div(getPageName(), field_button_name)));
    }

    /**
     * @param field_button_name [Field name]
     */
    @QAFTestStep(description = "Web: Click-Div Field {0}")
    @And("Web: Click-Div Field {string}")
    public static void clickDiv_Web(String field_button_name) throws Exception {
        BrowserGlobal.iClickOn(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.div(getPageName(), field_button_name)));
    }

    /**
     * @param field_navText_name [Field name]
     */
    @QAFTestStep(description = "Web: Click On-Nav Text Field {0}")
    @And("Web: Click On-Nav Text Field {string}")
    public static void clickOnNavText_Web(String field_navText_name) throws Exception {
        BrowserGlobal.iClickOn(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.text(getPageName(), field_navText_name)));
    }


    /**
     * @param field_text_name [text name to click]
     */
    @QAFTestStep(description = "Web: Click-Text Field:{0}")
    @And("Web: Click-Text Field:{string}")
    public static void clickText_Web(String field_text_name) throws Exception {
        BrowserGlobal.iClickOn(waitForFieldToBePresentScrollToCenterViewAndEnabled(patternLoc.text(getPageName(), field_text_name)));

    }

    @QAFTestStep(description = "Web: I assert text present in page {text}")
    @And("Web: I assert text present in page {string}")
    public static void iAssertTextPresentInPage_Web(String text) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.text(getPageName(),text));
        BrowserGlobal.iAssertTextPresentInPage(text);
    }

    /**
     * @param verification_msg [Screenshot message to show]
     */
    @QAFTestStep(description = "Web: Business Verification: I verify {0}")
    @And("Web: Business Verification: I verify {string}")
    public static void businessVerification_Web(String verification_msg) throws Exception {
        iWaitForPageToLoad_Web();
        web.waitForSecs_Web("2");
        BrowserGlobal.iTakeScreenshotWithComment(verification_msg);
    }

    @QAFTestStep(description = "Web: I wait for page to load")
    @And("Web: I wait for page to load")
    public static void iWaitForPageToLoad_Web() {
        BrowserGlobal.iWaitForPageToLoad();
    }
}



/*

//    Web: Open-Browser Url:{0}
//    Web: Open-Browser-And-Maximise Url:{0}
//    Web: Open-Browser-With-Set-Window-Size Url:{0} Width:{1} Height:{2}

//    Web: Click-Link Field:{0}
      Web: Click-Link Field:{0}
//    Web: Click-Button Field:{0}
    Web: Click-Switch-Button Field:{0}
    Web: Click-Checkbox Field:{0}
    Web: Click-Checkbox-With-Grouping Group-Header:{1} Field:{0}
    Web: Click-Radio-Button Field:{0}
    Web: Click-Tab Field:{0}

    Web: Double-Click Link Field:{0}
    Web: Double-Click Input-Text Field:{0}

//    Web: Input-Text Value:{0} Field:{1}
    Web: Input-Date Value:{0} Field:{1}
    Web: Input-Lookup Value:{0} Field:{1}
//    Web: Input-Text-With-Placeholder Value:{0} Field:{1}
    Web: Input-Text-By-Click Value:{0} Field:{1}
    Web: Input-Text-With-Placeholder-By-Click Value:{0} Field:{1} With-Placeholder

    Web: Select-Dropdown By-Text:{0} Field:{1}
    Web: Select-Dropdown By-Index:{0} Field:{1}
    Web: Select-Dropdown By-Value:{0} Field:{1}
    Web: Select-Dropdown By-Input-Text:{0} Field:{1}

    Web: Upload-File File-Name-with-Path:{0} Field:{1}
    Web: Upload-Last-Downloaded-File Field:{0}

    Web: Verify-Input-Text-Filled Value:{0} Field:{1}
    Web: Verify-Dropdown-Selected Value:{0} Field:{1}
    Web: Verify-Switch-Button-On Field:{0}
    Web: Verify-Switch-Button-Off Field:{0}
    Web: Verify-Checkbox-Selected Field:{0}
    Web: Verify-Checkbox-Not-Selected Field:{0}
    Web: Verify-Radio-Button-Selected Field:{0}
    Web: Verify-Radio-Button-Not-Selected Field:{0}

    Web: Verify-Table-Cell-Text Row:{0} Column:{1} Field:{2}
    Web: Verify-Table-Header-Text Column:{0} Field:{1}

    Web: Verify-Page-Title Value:{0} Page-Name:{1}
    Web: Verify-Page-Title-Partial Value:{0} Page-Name:{1}
    Web: Verify-Page-Contains-Text Value:{0} Page-Name:{1}
    Web: Verify-Page-Header-Text Field:{0} Page-Name:{1}
    Web: Verify-Page-Sub-Header-Text Field:{0} Page-Name:{1}

    Web: Verify-Page-Status-Is-200 [Page OK]
    Web: Verify-Page-Status-Is-Not-200 [Not-Page OK]
    Web: Verify-Page-Status-Is-404 [Page Not Found]
    Web: Verify-Page-Status-Is-Not-404 [Not-Page Not Found]
    Web: Verify-Page-Status-Is-500 [Internal Server Error]
    Web: Verify-Page-Status-Is-Not-500 [Not-Internal Server Error]
    Web: Verify-Page-Status Value:{}
    Web: Verify-Page-Status-Is-Not Value:{}

    Web: Verify-URL-Protocol-Is-HTTP
    Web: Verify-URL-Protocol-Is-HTTPS
    Web: Verify-URL-Domain Value:{}
    Web: Verify-URL-Path Value:{} (Without-Domain)
    Web: Verify-URL-Path-Contains Value:{} (Without-Domain)
    Web: Verify-URL-Path-Query-String Value:{} (Start with ?)
    Web: Verify-URL-Path-Hash Value:{} (Start with #)

    Web: Set Page-Name Value:{0}
    Web: Set Cookie-Value Key:{0} Value:{1}

    Web: Assign Input-Field-Value-To-Variable Var-Name:{0} Field:{1}
    Web: Assign Value-To-Variable Var-Name:{0} Value:{1}

    Web: Switch-Browser-Tab By-Index:{0}
    Web: Switch-Browser-Tab By-Name:{0}
    Web: Switch-Browser-Window By-Index:{0}
    Web: Switch-To-Main-Browser-Window
    Web: Switch-To-Iframes By-Id-Or-Name:{}
    Web: Switch-To-Iframes By-Index:{}
    Web: Switch-From-Iframes-To-Main-Window By-Id-Or-Name:{}

    Web: Close-Current-Tab



    Web: Page-Reload
    Web: Page-Back
    Web: Page-Forward


    Web: Cookie-Set Key:{} Value:{}
    Web: Cookie-Delete Key:{}
    Web: Cookie-Delete-All

    Web: Verify Cookie-Value Key:{} Verify-Value:{}
    Web: Verify Cookie-Is-Set Verify-Key:{}

    Web: Take-Screenshot
    Web: Take-Full-Screenshot  Ref: https://stackoverflow.com/questions/44085722/how-to-get-screenshot-of-full-webpage-using-selenium-and-java

    Web: Wait-Field-To-Be-Present-Scroll-And-Enabled Field:{0}
    Web: Wait-Field-Not-To-Be-Present Field:{0}
    Web: Wait-For-Milliseconds Value:{0}
    Web: Wait-For-Seconds Value:{0}





 */
