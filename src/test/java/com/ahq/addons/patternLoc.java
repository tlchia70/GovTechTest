package com.ahq.addons;

import com.ahq.globals.BrowserGlobal;
import org.apache.commons.text.CaseUtils;
import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

// fieldType [“input”, “link”, “text”, “radio_button”, “checkbox”, “select”, “button”, "tab" - Should match with last part of pattern entry Eg: loc.XXXX.pattern.radio_button]
public class patternLoc {
//    private static String patternCode = getBundle().getPropertyValue("loc.pattern.code");

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String input(String page, String fieldName) throws Exception{
        String fieldType = "input";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    public static String dropdown(String page, String fieldName) throws Exception{
        String fieldType = "dropdown";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String select(String page, String fieldName) throws Exception{
        String fieldType = "select";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }
    /**
     * @param page [Page of the locator]
     * @param fieldName [Link Name]
     *
     **/
    public static String link(String page, String fieldName) throws Exception{
        String fieldType = "link";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Link Name]
     *
     **/
    public static String value(String page, String fieldName) throws Exception{
        String fieldType = "value";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Link Name]
     *
     **/
    public static String link(String page, String fieldName, String section) throws Exception{
        String fieldType = section.trim().toLowerCase() + ".link";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }
//    /**
//     * @param page [Page of the locator]
//     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
//     * @param fieldInstance [Field Instance: Only mention if multiple instance of the same field exists]
//     * @param ByLabelCheck [ByLabelCheck: Only mention if multiple instance of the same field exists]
//     **/
//    public static String input(String page, String fieldName, Boolean ByLabelCheck) throws Exception{
//        String fieldType = "input";
//        String locator = checkLoc(page, fieldType, fieldName);
//        if (locator.contains("auto.")) {
//            forValue(fieldName, fieldType);
//            if (!fieldInstance.isBlank() || !fieldInstance.isEmpty()) { getBundle().setProperty("loc.auto.fieldInstance",fieldInstance.trim()); }
//        }
//        return locator;
//    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String button(String page, String fieldName) throws Exception{
        String fieldType = "button";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String buttonLookupLaunch(String page, String fieldName) throws Exception{
        String fieldType = "lookupLaunch";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    public static String loadingModal(String page, String fieldName) throws Exception{
        String fieldType = "loadingModal";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String buttonLookupClear(String page, String fieldName) throws Exception{
        String fieldType = "lookupClear";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }
    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String checkbox(String page, String fieldName) throws Exception{
        String fieldType = "checkbox";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    public static String amSession(String page, String day) throws Exception {
        String fieldType = "amSession";
        String locator = checkLoc(page, fieldType, day);
        if (locator.contains("auto.")) {
            generateLoc(locator, day, fieldType);
        }
        return locator;
    }

    public static String pmSession(String page, String day) throws Exception {
        String fieldType = "pmSession";
        String locator = checkLoc(page, fieldType, day);
        if (locator.contains("auto.")) {
            generateLoc(locator, day, fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String checkboxWithFieldSet(String page, String fieldName, String fieldValue) throws Exception{
        String fieldType = "checkbox.fieldSet";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            getBundle().setProperty("loc.auto.fieldValue",fieldNameCheck(fieldValue));
            if (fieldInstanceCheck(fieldValue).equalsIgnoreCase("1")) {
                generateLoc(locator,fieldName,fieldType);
            } else {
                generateLoc(locator,fieldName+"["+fieldInstanceCheck(fieldValue)+"]",fieldType);
            }

        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String radioButton(String page, String fieldName, String fieldValue) throws Exception{
        String fieldType = "radioButton";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            getBundle().setProperty("loc.auto.fieldValue",fieldValue);
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String actionMenu(String page, String fieldName) throws Exception{
        String fieldType = "actionMenu";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String actionMenuDropdown(String page, String fieldName) throws Exception{
        String fieldType = "actionMenu.dropdown";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }
    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     **/
    public static String header(String page, String fieldName) throws Exception{
        String fieldType = "header";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {  generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     **/
    public static String subHeader(String page, String fieldName) throws Exception{
        String fieldType = "subHeader";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {  generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     **/
    public static String listHeader(String page, String fieldName) throws Exception{
        String fieldType = "listHeader";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     **/
    public static String errorText(String page, String fieldName) throws Exception{
        String fieldType = "errorText";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {  generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     **/
    public static String text(String page, String fieldName) throws Exception{
        String fieldType = "text";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {  generateLoc(locator,fieldName,fieldType); }
        return locator;
    }
    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     **/
    public static String breadcrumb(String page, String fieldName) throws Exception{
        String fieldType = "breadcrumb";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String item(String page, String fieldName) throws Exception{
        String fieldType = "item";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String spinner(String page, String fieldName) throws Exception{
        String fieldType = "spinner";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String addItem(String page, String fieldName) throws Exception{
        String fieldType = "addItem";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }


    private static void forValue(String argFieldName, String argFieldType) {
        getBundle().setProperty("loc.auto.fieldName",fieldNameCheck(argFieldName));
        getBundle().setProperty("loc.auto.fieldInstance",fieldInstanceCheck(argFieldName));
        String locLabelPatternName = getPatternCode()+".pattern.label";
        String locLabelPatternVal = getBundle().getPropertyValue(locLabelPatternName);
        String forValue;
        if (locLabelPatternVal.equals(locLabelPatternName) || locLabelPatternVal.length() < 5) {
            System.out.println("=====>[ERROR] => [Locator Pattern '"+ locLabelPatternName + "' not available]");
        } else {
            String locLabelValue = "{\"locator\":["+locLabelPatternVal+"],\"desc\":\""+fieldNameCheck(argFieldName)+" : [LABEL] Field \"}";
            getBundle().setProperty("loc.auto.label",locLabelValue);
            try {
                BrowserGlobal.iWaitUntilElementPresent("loc.auto.label");
                forValue = BrowserGlobal.iGetAttributeValueFrom("for","loc.auto.label");
//                forValue = $("loc.auto.label").getAttribute("for");
                getBundle().setProperty("loc.auto.forValue",forValue);
                // Locator Logging
                if (getBundle().getPropertyValue("loc.pattern.log").trim().equalsIgnoreCase("true")) {
                    System.out.println("==== AUTO GENERATED: LOCATOR (Pattern) ====> " + "loc.auto.label" + "=" + getBundle().getPropertyValue("loc.auto.label"));
                }
            } catch (Exception e) {
            }
        }
    }

    private static String checkLoc(String argPageName, String argFieldType, String argFieldName) {
//        String projCode = getBundle().getPropertyValue("project.code");
        getBundle().setProperty("loc.auto.fieldName","");
        getBundle().setProperty("loc.auto.fieldInstance","");
        getBundle().setProperty("loc.auto.forValue","");
        getBundle().setProperty("loc.auto.fieldValue","");

        String patternCode = getPatternCode();
        String locName = patternCode +"."+ CaseUtils.toCamelCase(argPageName.replaceAll("[^a-zA-Z0-9]", " "), false, ' ') + "." + CaseUtils.toCamelCase(argFieldType.replaceAll("d365_", "").replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim() + "." + CaseUtils.toCamelCase(argFieldName.replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim();
        String locVal = getBundle().getPropertyValue(locName);
        if (locVal.equals(locName) || locVal.length() < 5) {
            locName = "auto." + locName;
        }
        return locName;
    }

    private static void generateLoc(String argLocator, String argFieldName, String argFieldType) {
//      Checking and assigning pattern code
        String patternCodeVal = getPatternCode();

        // Assigning field name
        getBundle().setProperty("loc.auto.fieldName",fieldNameCheck(argFieldName));
        getBundle().setProperty("loc.auto.fieldInstance",fieldInstanceCheck(argFieldName));
        String locPattern = patternCodeVal+".pattern."+argFieldType;        // Generating locator pattern code
        String locPatternVal = getBundle().getPropertyValue(locPattern);    // Getting locator pattern value
        if (locPatternVal.equals(locPattern) || locPatternVal.length() < 5) {
            System.out.println("=====>[ERROR] => [Locator Pattern '"+ locPattern + "' not available]");
        } else {
            getBundle().setProperty(argLocator,"{\"locator\":["+locPatternVal+"],\"desc\":\""+argFieldName + " : ["+argFieldType+"] Field \"}");
        }
    }

    private static String getPatternCode() {
        String patternCodeVal = getBundle().getPropertyValue("loc.pattern.code");
        if (patternCodeVal.equals("loc.pattern.code")) {
            System.out.println("=====>[ERROR] => [Locator Pattern Code missing in project config or not assigned during execution]");
        }
        return patternCodeVal;
    }

    private static String fieldNameCheck(String argFieldName){
        if (argFieldName.contains("[") && argFieldName.endsWith("]")) {
            String[] fName = argFieldName.split("\\[");
            return fName[0].trim();
        } else {
            return argFieldName.trim();
        }
    }
    private static String fieldInstanceCheck(String argFieldName){
        if (argFieldName.contains("[") && argFieldName.endsWith("]")) {
            String[] fName = argFieldName.split("\\[");
            return fName[1].replace("]","");
        } else {
            return "1";
        }
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *                  Author - Bharat
     **/
    public static String graphlink(String page, String fieldName) throws Exception{
        String fieldType = "graphlink";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }
    public static String dataLabel(String page, String fieldName) throws Exception{
        String fieldType = "dataLabel";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {  generateLoc(locator,fieldName,fieldType); }
        return locator;
    }
    public static String sheet(String page, String fieldName) throws Exception{
        String fieldType = "sheet";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String navbar(String page, String fieldName) throws Exception{
        String fieldType = "navbar";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String submenu(String page, String fieldName) throws Exception{
        String fieldType = "submenu";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String card(String page, String fieldName) throws Exception{
        String fieldType = "card";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String accordion(String page, String fieldName) throws Exception{
        String fieldType = "accordion";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String video(String page, String fieldName) throws Exception{
        String fieldType = "video";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String dropdownOption(String page, String fieldName) throws Exception{
        String fieldType = "dropdownOption";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String div(String page, String fieldName) throws Exception{
        String fieldType = "div";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String error(String page, String fieldName) throws Exception{
        String fieldType = "error";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String graphTab(String page, String fieldName) throws Exception{
        String fieldType = "graphTab";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }



    public static String row(String page, String fieldName) throws Exception{
        String fieldType = "row";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String rowColumn(String page, String row, String column) throws Exception {
        String fieldType = "rowColumn";
        String locator = checkLoc(page, fieldType, "");
        System.out.println("Initial locator: " + locator);

        if (locator.contains("auto.")) {
            locator = locator.replace("${loc.auto.rowNumber}", row);
            locator = locator.replace("${loc.auto.colNumber}", column);
            System.out.println("Locator after replacement: " + locator);
        } else {
            System.out.println("Locator does not contain 'auto.'");
        }

        return locator;
    }

    public static String contextMenuOption(String page, String fieldName) throws Exception{
        String fieldType = "contextMenuOption";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String table(String page, String fieldName) throws Exception{
        String fieldType = "table";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String timetableData(String page, String fieldName) throws Exception{
        String fieldType = "timetableData";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String kpi(String page, String fieldName) throws Exception{
        String fieldType = "kpi";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String tab(String page, String fieldName) throws Exception{
        String fieldType = "tab";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String dataTree(String page, String fieldName) throws Exception{
        String fieldType = "dataTree";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String menu(String page, String fieldName) throws Exception{
        String fieldType = "menu";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String label(String page, String fieldName) throws Exception{
        String fieldType = "label";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String icon(String page, String fieldName) throws Exception{
        String fieldType = "icon";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String list(String page, String fieldName) throws Exception{
        String fieldType = "list";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String img(String page, String fieldName) throws Exception{
        String fieldType = "img";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String date(String page, String fieldName) throws Exception{
        String fieldType = "date";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String datePicker(String page, String fieldName) throws Exception{
        String fieldType = "datePicker";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String dateRange(String page, String fieldName) throws Exception{
        String fieldType = "dateRange";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String tabContent(String page, String fieldName) throws Exception{
        String fieldType = "tabContent";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String toast(String page, String fieldName) throws Exception{
        String fieldType = "toast";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String textarea(String page, String fieldName) throws Exception{
        String fieldType = "textarea";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String tableHeader(String page, String fieldName) throws Exception{
        String fieldType = "tableHeader";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,fieldType); }
        return locator;
    }

    public static String getLocatorOfElement(String page, String elementName, String fieldName) throws Exception{
        String locator = checkLoc(page, elementName, fieldName);
        if (locator.contains("auto.")) { generateLoc(locator,fieldName,elementName); }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     *
     **/
    public static String radioButton(String page, String fieldName) throws Exception{
        String fieldType = "radioButton";
        String locator = checkLoc(page, fieldType, fieldName);
        if (locator.contains("auto.")) {
            generateLoc(locator,fieldName,fieldType);
        }
        return locator;
    }


}