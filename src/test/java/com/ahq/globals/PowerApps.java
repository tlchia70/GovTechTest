package com.ahq.globals;

import com.ahq.addons.patternLoc;
import com.qmetry.qaf.automation.step.QAFTestStep;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

public class PowerApps {

    /**
     * @param url [Open browser with URL]

     */
    @QAFTestStep(description = "PowerApps: Open Url:{0}")
    public static void openUrl_PowerApps(String url) throws Exception {
        BrowserGlobal.iOpenWebBrowser(url);
    }

    /**
     * @param text [text to fill]
     * @param field [Field name]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Input Text:{0} Field:{1} Page:{2}")
    public static void InputText_PowerApps(String text,String field,String page) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.input(page,field));
        BrowserGlobal.iScrollToAnElement(patternLoc.input(page,field));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.input(page,field));
        BrowserGlobal.iInputInTo(text, patternLoc.input(page,field));
    }

    /**
     * @param text [text to fill]
     * @param field [Field name]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Input Password:{0} Field:{1} Page:{2}")
    public static void inputPassword_PowerApps(String text,String field,String page) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.input(page,field));
        BrowserGlobal.iScrollToAnElement(patternLoc.input(page,field));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.input(page,field));
        BrowserGlobal.iInputInTo(text, patternLoc.input(page,field));
    }

    /**
     * @param field [Field name]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Click-Button Field:{0} Page:{1}")
    public static void clickButton_PowerApps(String field,String page) throws Exception {
        BrowserGlobal.iScrollToAnElement(patternLoc.button(page,field));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.button(page,field));
        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.button(page,field));
        BrowserGlobal.iClickOn(patternLoc.button(page,field));

    }
    /**
     * @param field [Field name]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Click-Checkbox Field:{0} Page:{1}")
    public static void clickCheckbox_PowerApps(String field,String page) throws Exception {
        BrowserGlobal.iScrollToAnElement(patternLoc.checkbox(page,field));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.checkbox(page,field));
        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.checkbox(page,field));
        BrowserGlobal.iClickOn(patternLoc.checkbox(page,field));
    }
    /**
     * @param field [Field name]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Click-Checkbox Field:{0} Value:{1} Page:{2}")
    public static void clickCheckboxWithFieldSet_PowerApps(String field, String value, String page) throws Exception {
        String[] chkBoxValArray = value.split(",");
        for(String chkBoxVal : chkBoxValArray){
            BrowserGlobal.iWaitUntilElementPresent(patternLoc.checkboxWithFieldSet(page,field,chkBoxVal));
            BrowserGlobal.iScrollToAnElement(patternLoc.checkboxWithFieldSet(page,field,chkBoxVal));
            BrowserGlobal.iWaitUntilElementVisible(patternLoc.checkboxWithFieldSet(page,field,chkBoxVal));
            BrowserGlobal.iClickOn(patternLoc.checkboxWithFieldSet(page,field,chkBoxVal));
        }
    }

    /**
     * @param field [Field name]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Click-Action-Menu-And-Select Field:{0} Page:{1}")
    public static void clickActionMenuAndSelect_PowerApps(String field, String page) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.actionMenu(page,field));
        BrowserGlobal.iScrollToAnElement(patternLoc.actionMenu(page,field));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.actionMenu(page,field));
        BrowserGlobal.iClickOn(patternLoc.actionMenu(page,field));

        BrowserGlobal.iWaitUntilElementPresent(patternLoc.actionMenuDropdown(page,field));
        BrowserGlobal.iScrollToAnElement(patternLoc.actionMenuDropdown(page,field));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.actionMenuDropdown(page,field));
        BrowserGlobal.iClickOn(patternLoc.actionMenuDropdown(page,field));
    }

    /**
     * @param field [Field name]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Click-Radio-Button Field:{0} Value:{1} Page:{2}")
    public static void clickRadioButton_PowerApps(String field,String value, String page) throws Exception {
        BrowserGlobal.iScrollToAnElement(patternLoc.radioButton(page,field,value));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.radioButton(page,field,value));
        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.radioButton(page,field,value));
        BrowserGlobal.iClickOn(patternLoc.radioButton(page,field,value));
    }

    /**
     * @param link_text [Link text to be clicked]
     */
    @QAFTestStep(description = "PowerApps: Click-Link Text:{0} Page:{1}")
    public static void clickLink_PowerApps(String link_text,String page) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.link(page,link_text));
        BrowserGlobal.iScrollToAnElement(patternLoc.link(page,link_text));
        BrowserGlobal.iClickOn(patternLoc.link(page,link_text));
    }
    /**
     * @param dropdown_Text [Text to be selected in dropdown]
     * @param field [Field name]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Select Field:{0} Value:{1} Page:{2}")
    public static void select_PowerApps(String field, String dropdown_Text, String page) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.select(page,field));
        BrowserGlobal.iScrollToAnElement(patternLoc.select(page,field));
        BrowserGlobal.iSelectDropdownWithText(patternLoc.select(page,field),dropdown_Text);
    }

    /**
     * @param title_Text [iFrames Title Text]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Switch-To-Popup-With-Title Text:{0} Page:{1}")
    public static void switchToPopupWithTitle_PowerApps(String title_Text, String page) throws Exception {
        String iFrameStatus = getBundle().getPropertyValue("iframe.auto.status");
        if (iFrameStatus.equalsIgnoreCase("iframe.auto.status") || iFrameStatus.equalsIgnoreCase("mainWindow")) {
            BrowserGlobal.iWaitUntilElementPresent("xpath=(//iframe[@title='"+title_Text+"'])[1]");
            BrowserGlobal.iWaitUntilElementVisible("xpath=(//iframe[@title='"+title_Text+"'])[1]");
            BrowserGlobal.iSwitchToIFrameByTitle(title_Text);
        } else {
            BrowserGlobal.iSwitchToParentWindowFrame();
            BrowserGlobal.iWaitUntilElementPresent("xpath=(//iframe[@title='"+title_Text+"'])[1]");
            BrowserGlobal.iWaitUntilElementVisible("xpath=(//iframe[@title='"+title_Text+"'])[1]");
            BrowserGlobal.iSwitchToIFrameByTitle(title_Text);
            getBundle().setProperty("iframe.auto.status","insideIframe");
        }
    }

    /**
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Switch-To-Parent-Window Page:{0}")
    public static void switchToParentWindow_PowerApps(String page) throws Exception {
        BrowserGlobal.iSwitchToParentWindowFrame();
        getBundle().setProperty("iframe.auto.status","mainWindow");
    }
//    Click-Radio-Button Field:"Do you have any other approvals or advice that relates to this application?" Value:"Yes" Page:"Subdivision Application"
    //=============== VERIFY =========================
    /**
     * @param header_text [Header text to be verified]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Wait-And-Verify-Page-Header Text:{0} Page:{1}")
    public static void waitAndVerifyPageHeader_PowerApps(String header_text,String page) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iAssertElementText(patternLoc.header(page,header_text),header_text);

//        BrowserGlobal.iAssertElementPresent(patternLoc.header(page,header_text));
    }

    /**
     * @param subHeader_text [Sub Header text to be verified]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Wait-And-Verify-Page-Sub-Header Text:{0} Page:{1}")
    public static void waitAndVerifyPageSubHeader_PowerApps(String subHeader_text,String page) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iAssertElementPresent(patternLoc.subHeader(page,subHeader_text));
    }
    /**
     * @param header_text [Header text to be verified]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Wait-And-Verify-Page-Title Text:{0} Page:{1}")
    public static void waitAndVerifyPageTitle_PowerApps(String header_text,String page) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
//        BrowserGlobal.iAssertElementText(patternLoc.header(page,header_text,"1"),header_text);
        BrowserGlobal.iAssertTitlePartialText(header_text);
    }

    /**
     * @param breadcrumb_text [Breadcrumb text to be verified]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Wait-And-Verify-Page-Breadcrumb Text:{0} Page:{1}")
    public static void waitAndVerifyPageBreadcrumb_PowerApps(String breadcrumb_text,String page) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iAssertElementPresent(patternLoc.breadcrumb(page,breadcrumb_text));
//        BrowserGlobal.iAssertElementText(patternLoc.header(page,header_text,"1"),header_text);
//        BrowserGlobal.iAssertTitlePartialText(header_text);
    }
    /**
     * @param title_text [Title text to be verified]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Wait-And-Verify-Page-Title-Contains Text:{0} Page:{1}")
    public static void waitAndVerifyPageTitleContains_PowerApps(String title_text,String page) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iAssertTitlePartialText(title_text);
    }

    /**
     * @param listHeader_text [List header text to be verified]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Wait-And-Verify-List-Header Text:{0} Page:{1}")
    public static void waitAndVerifyListHeader_PowerApps(String listHeader_text,String page) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iAssertElementPresent(patternLoc.listHeader(page,listHeader_text));
    }

    /**
     * @param text [Error Text to be Verified]
     * @param page [Page name]
     */
    @QAFTestStep(description = "PowerApps: Verify-Error-Text:{0} Page:{1}")
    public static void verifyErrorText_InPowerApps(String text, String page) throws Exception {
        BrowserGlobal.iAssertElementPresent(patternLoc.errorText(page,text));
    }





    private static String pageNameCheck(String argPage){
        if (argPage.contains("::")) {
            String[] pageSplit = argPage.trim().split("::");
            return pageSplit[0];
        } else {
            return argPage;
        }
    }
    private static String fieldLocCheck(String argPage, String argFieldName, String argDefaultLoc){
        String fLoc ="";
        if (argPage.contains("::")) {
            String[] pageSplit = argPage.trim().split("::");
            fLoc = pageSplit[1];
        } else {
            fLoc = argDefaultLoc;
        }

        if (argFieldName.startsWith("[") && argFieldName.contains("]")) {
            fLoc = fLoc + "::" + argFieldName.substring(1, argFieldName.indexOf("]"));
            System.out.println("==fLoc==> " + fLoc);
        }
        return fLoc.trim();
    }

    private static String fieldNameCheck(String argFieldName){
        if (argFieldName.startsWith("[") && argFieldName.contains("]")) {
            String[] fName = argFieldName.trim().split("]");
            return fName[1];
        } else {
            return argFieldName.trim();
        }
    }
}
