package com.ahq.addons;

import com.ahq.globals.BrowserGlobal;
import org.apache.commons.text.CaseUtils;

import java.util.Optional;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

public class loc {
    /**
     * @param page      [Page of the locator]
     * @param fieldType [“input”, “link”, “text”, “radioButton”, “checkbox”, “select”, “button”, "tab"]
     * @param fieldName [Field Name: Eg: First Name]
     * @return
     * @throws Exception
     */
    public static String get(String page, String fieldType, String fieldName) throws Exception{

        String locator;
        String locVal;
        System.out.println("== fieldName ==> " + fieldName.trim());
        getBundle().setProperty("loc.auto.fieldName",fieldName.trim());
//        if (!fieldName.contains(":") && !fieldType.contains("d365_")) {
//            locator = "loc." + CaseUtils.toCamelCase(page.replaceAll("[^a-zA-Z0-9]", " "), false, ' ') + "." + CaseUtils.toCamelCase(fieldType.replaceAll("d365_", "").replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim() + "." + CaseUtils.toCamelCase(fieldName.replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim();
//            locVal = getBundle().getPropertyValue(locator);
//            String forValue = null;
//            if (locator.equals(locVal) || locVal.length() < 5) {
//                locator = "auto." + locator;
//                getBundle().setProperty("auto.loc.id","NO-ID");
//                if (fieldType.toLowerCase().trim().equals("input") || fieldType.toLowerCase().trim().equals("inputselect")) {
//                    try{
//                        String locLabel = "{\"locator\":["+getBundle().getPropertyValue("loc.patternLoc.label")+"],\"desc\":\""+fieldName+" AUTO GEN LABEL FIELD\"}";
////                        getBundle().setProperty("auto.loc.label","{\"locator\":["+locLabel+"],\"desc\":\""+fieldName+" AUTO GEN LABEL FIELD\"}");
////                        getBundle().setProperty("auto.loc.label","{\"locator\":[\"xpath=//LABEL[text()='"+fieldName.trim()+"']\",\"xpath=//LABEL[@title='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" AUTO GEN LABEL FIELD\"}");
//                        BrowserGlobal.iScrollToAnElement(locLabel);
//                        BrowserGlobal.iWaitUntilElementVisibleWithTimeout(locLabel,"2");
////                        getBundle().setProperty("auto.loc.label","{\"locator\":[\"xpath=//LABEL[text()='"+fieldName.trim()+"']\",\"xpath=//LABEL[@title='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" AUTO GEN LABEL FIELD\"}");
//                        forValue = $(locLabel).getAttribute("for");
//                    } catch (Exception e) {
//
//                    }
//                }
//                switch(fieldType.toLowerCase().trim()) {
//                    case "input":
//                        if (forValue != null && !forValue.equals(fieldName)) {
//                            getBundle().setProperty(locator,"{\"locator\":[\"xpath=//input[@id='"+forValue.trim()+"'][@type='text']\",\"xpath=//textarea[@id='"+forValue.trim()+"']\"],\"desc\":\""+fieldName+" INPUT: AUTO GEN USING FOR LOCATOR\"}");
//                        } else {
//                            getBundle().setProperty(locator,"{\"locator\":[\"xpath=//input[@aria-label='"+fieldName.trim()+"']\",\"xpath=//input[@id='"+fieldName.trim()+"']\",\"xpath=//input[@title='"+fieldName.trim()+"']\",\"xpath=//input[@placeholder='"+fieldName.trim()+"']\",\"id="+fieldName+"\",\"id="+fieldName.replaceAll("\\s+","").toLowerCase()+"\",\"name="+fieldName.replaceAll("\\s+","").toLowerCase()+"\",\"xpath=//*[contains(@aria-label,'"+fieldName.trim()+"')]\",\"xpath=//*[contains(@title,'"+fieldName.trim()+"')]\",\"xpath=//*[contains(@value,'"+fieldName.trim()+"')]\",\"xpath=//*[contains(text(),'"+fieldName.trim()+"')]\"],\"desc\":\""+fieldName+" field\"}");
//                        }
//                        break;
////                        case y:
////                            // code block
////                            break;
//                    default:
//                        getBundle().setProperty(locator,"{\"locator\":[\"xpath=//*[@aria-label='"+fieldName.trim()+"']\",\"xpath=//*[@title='"+fieldName.trim()+"']\",\"xpath=//*[@placeholder='"+fieldName.trim()+"']\",\"xpath=//*[@value='"+fieldName.trim()+"']\",\"id="+fieldName+"\",\"id="+fieldName.replaceAll("\\s+","").toLowerCase()+"\",\"name="+fieldName.replaceAll("\\s+","").toLowerCase()+"\",\"xpath=//*[contains(@aria-label,'"+fieldName.trim()+"')]\",\"xpath=//*[contains(@title,'"+fieldName.trim()+"')]\",\"xpath=//*[contains(@value,'"+fieldName.trim()+"')]\",\"xpath=//*[contains(text(),'"+fieldName.trim()+"')]\"],\"desc\":\""+fieldName+" field\"}");
//                }
//            }
//
//        } else

            if (fieldName.contains(":") && !fieldType.contains("d365_")) {
            String[] fieldSplit = fieldName.split(":");

            locator = "loc." + CaseUtils.toCamelCase(page.replaceAll("[^a-zA-Z0-9]", " "), false, ' ') + "." + CaseUtils.toCamelCase(fieldType.replaceAll("d365_", "").replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim() + "." + CaseUtils.toCamelCase(fieldSplit[0].replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim();
            locVal = getBundle().getPropertyValue(locator);
            if (locator.equals(locVal) || locVal.length() < 5) {
                if (fieldSplit.length == 3) {
                    locator = "auto."+ locator;
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//*[@"+fieldSplit[1].trim()+"='"+fieldSplit[2].trim()+"']\"],\"desc\":\""+fieldName+" field\"}");
                    System.out.println("==== AUTO GENERATED LOCATOR [1] ====> " + locator + "=" + getBundle().getPropertyValue(locator));
                } else {
                    locator = "auto."+ locator;
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//*[@"+fieldSplit[1].trim()+"='"+fieldSplit[0].trim()+"']\",\"xpath=//*[@"+fieldSplit[1].trim()+"='"+fieldSplit[0].trim().toLowerCase()+"']\"],\"desc\":\""+fieldName+" field\"}");
                    System.out.println("==== AUTO GENERATED LOCATOR [2] ====> " + locator + "=" + getBundle().getPropertyValue(locator));
                }
            }
        } else {
            locator = "loc." + CaseUtils.toCamelCase(page.replaceAll("[^a-zA-Z0-9]", " "), false, ' ') + "." + CaseUtils.toCamelCase(fieldType.replaceAll("d365_", "").replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim() + "." + CaseUtils.toCamelCase(fieldName.replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim();
            locVal = getBundle().getPropertyValue(locator);
            if (locator.equals(locVal) || locVal.length() < 5) {
                locator = "auto."+ locator;
                if (fieldType.trim().equalsIgnoreCase("d365_link") || fieldType.trim().equalsIgnoreCase("d365_tab")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//a[@aria-label='"+fieldName.trim()+"']\",\"xpath=//a[@title='"+fieldName.trim()+"']\",\"xpath=//li[@aria-label='"+fieldName.trim()+"']\",\"xpath=//li[@title='"+fieldName.trim()+"']\",\"xpath=//a[contains(@aria-label,'"+fieldName.trim()+"')]\",\"xpath=//a[contains(@title,'"+fieldName.trim()+"')]\",\"xpath=//li[contains(@aria-label,'"+fieldName.trim()+"')]\",\"xpath=//li[contains(@title,'"+fieldName.trim()+"')]\"],\"desc\":\""+fieldName+" field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_header") || fieldType.trim().equalsIgnoreCase("d365_system_view") ){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//h1[@title='"+fieldName.trim()+"']\",\"xpath=//h1[@aria-label='"+fieldName.trim()+"']\",\"xpath=//div[@aria-label='"+fieldName.trim()+"']\",\"xpath=//h1[contains(@title,'"+fieldName.trim()+"')]\",\"xpath=//div[contains(@aria-label,'"+fieldName.trim()+"')]\"],\"desc\":\""+fieldName+" field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_system_view_list")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[@aria-label='View Options'][@role='dialog']//descendant::label[text()='"+fieldName.trim()+"']\",\"xpath=//ul[@aria-label='Select a view.'][@role='listbox']//descendant::li[@aria-label='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_date")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//input[@aria-label='Date of "+fieldName.trim()+"']\",\"xpath=//input[@aria-label='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_time")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//input[@aria-label='Time of "+fieldName.trim()+"']\",\"xpath=//input[@aria-label='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" field\"}");
                }else if (fieldType.trim().equalsIgnoreCase("d365_input")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//input[@aria-label='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_lookup")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//input[@aria-label='"+fieldName.trim()+", Lookup']\",\"xpath=//input[@aria-label='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_lookup_ext_value")){
                    String[] lookupfieldSplit = fieldName.trim().split(":");
                    String[] lookupfieldNameSplit = lookupfieldSplit[0].trim().replaceAll(" ","_").split("_");
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[contains(@data-id,'fieldControl-LookupResultsDropdown')][@title='"+lookupfieldSplit[1].trim()+"'][contains(@id,'"+lookupfieldNameSplit[0].trim().toLowerCase()+"')]\"],\"desc\":\""+lookupfieldSplit[0]+" field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_filter")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=(//input[@placeholder='Filter by keyword'])["+fieldName.trim()+"]\",\"xpath=(//*[contains(@data-id,'quickFind_text')])["+fieldName.trim()+"]\"],\"desc\":\""+fieldName+" field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_button")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//button[@aria-label='"+fieldName.trim()+"']\",\"xpath=//button[@title='"+fieldName.trim()+"']\",\"xpath=//button[contains(@title,'"+fieldName.trim()+"')]\"],\"desc\":\""+fieldName+" field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_button_popup")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[contains(@id,'dialogView')]/descendant::button[@aria-label='"+fieldName.trim()+"']\",\"xpath=//div[contains(@id,'modalDialogView')]/descendant::button[@aria-label='"+fieldName.trim()+"']\",\"xpath=//div[contains(@id,'dialogView')]/descendant::span[text()='"+fieldName.trim()+"']\",\"xpath=//div[contains(@id,'modalDialogView')]/descendant::span[text()='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" : Popup button field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_select")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//select[@aria-label='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" select field\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_header_control_list")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[contains(@id ,'headerControlsList')]//descendant::div[text()='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+": Header Control List Text\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_error_text")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//span[contains(@id,'error-message')][contains(text(),'"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+": Error Message Text\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_show_hide_button")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//i[@title='Show/Hide "+fieldName.trim()+"']\",\"xpath=//i[contains(@title='Show/Hide "+fieldName.trim()+"')]\"],\"desc\":\""+fieldName+": Show Hide Button\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_header_column")){
                    String[] headerElementSplit = fieldName.trim().split("::");
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=((//div[@class='ag-header-container'])[1]/descendant::div[@aria-colindex='"+headerElementSplit[1]+"']/descendant::div[text()='"+headerElementSplit[0]+"'])[1]\",\"xpath=//div[@aria-rowindex='1'][@aria-label='Header']/descendant::div[@aria-colindex='"+headerElementSplit[1]+"'][@title='"+headerElementSplit[0]+"']\"],\"desc\":\""+headerElementSplit[0]+" Table Header\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_header_dropdown")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[contains(@class,'ms-ContextualMenu-container')]/descendant::button[@aria-label='"+fieldName+"']\"],\"desc\":\""+fieldName+": Table Header dropdown Button\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_header_dropdown_sort")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[contains(@class,'ms-ContextualMenu-container')]/descendant::button[@aria-label='"+fieldName+"']/descendant::i[@data-icon-name='CheckMark']\"],\"desc\":\""+fieldName+": Table Header dropdown sort button\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_header_column_sort_up")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[@data-id='btnheaderselectcolumn']/following-sibling::div[@title='"+fieldName+"']/descendant::i[@data-icon-name='SortUp']\"],\"desc\":\""+fieldName+": TableColumn Sort up\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_header_column_sort_down")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[@data-id='btnheaderselectcolumn']/following-sibling::div[@title='"+fieldName+"']/descendant::i[@data-icon-name='SortDown']\"],\"desc\":\""+fieldName+": TableColumn Sort Down\"}");
                }  else if (fieldType.trim().equalsIgnoreCase("d365_table_cell")){
                    String[] cellInfoSplit = fieldName.trim().split("::");
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[@aria-rowindex='"+cellInfoSplit[1]+"']/descendant::div[@aria-colindex='"+cellInfoSplit[2]+"']\"],\"desc\":\""+fieldName.trim() + " Table Cell\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_cell_value")){
                    String[] cellInfoSplit = fieldName.trim().split("::");
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//div[@aria-rowindex='"+cellInfoSplit[1]+"']/descendant::div[@aria-colindex='"+cellInfoSplit[2]+"']//label[@aria-label='"+cellInfoSplit[3]+"']\"],\"desc\":\""+fieldName.trim() + " Table Cell\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_header_edit_column")){
                    String[] headerElementSplit = fieldName.trim().split("::");
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=(//div[@role='listitem'])["+headerElementSplit[1]+"][@aria-label='"+headerElementSplit[0]+"']/descendant::span[text()='"+headerElementSplit[0]+"']\"],\"desc\":\""+headerElementSplit[0]+" Table Header\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_horizontal_scroll")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=(//div[@class='ag-body-horizontal-scroll-viewport'])[1]\"],\"desc\":\""+fieldName+": Show Hide Button\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_select_instance")){
//                    String[] tableInfoSplit = fieldName.trim().split("::");
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=(//button[contains(@aria-label,'More commands for')])["+fieldName.trim()+"]\"],\"desc\":\"Select Table Instance "+fieldName.trim() +"\"}");
                } else if (fieldType.trim().equalsIgnoreCase("d365_table_see_all_records")){
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//button[@aria-label='See all records']\"],\"desc\":\"Table - See all records\"}");
                } else{
                    getBundle().setProperty(locator,"{\"locator\":[\"xpath=//*[@aria-label='"+fieldName.trim()+"']\",\"xpath=//*[@title='"+fieldName.trim()+"']\",\"xpath=//*[@placeholder='"+fieldName.trim()+"']\",\"xpath=//*[@value='"+fieldName.trim()+"']\",\"id="+fieldName+"\",\"id="+fieldName.replaceAll("\\s+","").toLowerCase()+"\",\"name="+fieldName.replaceAll("\\s+","").toLowerCase()+"\",\"xpath=//*[contains(@aria-label,'"+fieldName.trim()+"')]\",\"xpath=//*[contains(@title,'"+fieldName.trim()+"')]\",\"xpath=//*[contains(@value,'"+fieldName.trim()+"')]\",\"xpath=//*[contains(text(),'"+fieldName.trim()+"')]\"],\"desc\":\""+fieldName+" field\"}");
                }
                System.out.println("==== AUTO GENERATED LOCATOR [3]====> " + locator + "=" + getBundle().getPropertyValue(locator));
            }
        }
        return locator;
    }

    /**
     * @param page [Page of the locator]
     * @param fieldType [“input”, “link”, “text”, “radio_button”, “checkbox”, “select”, “button”, "tab" - Should match with last part of patternLoc entry Eg: loc.XXXX.patternLoc.radio_button]
     * @param fieldName [Field Name: Eg: First Name - Note for Radio button use ":" tell the value Eg: Choose Payment Type:Cash]
     * @param fieldInstance [Field Instance: Only mention if multiple instance of the same field exists]
     **/
    public static String pattern(String page, String fieldType, String fieldName, String fieldInstance) throws Exception{

        String locator;
        String fieldNamePrint = "";
        fieldType = fieldType.toLowerCase().trim();
        String patternCode = getBundle().getPropertyValue("loc.pattern.code");
        locator = patternCode + CaseUtils.toCamelCase(page.replaceAll("[^a-zA-Z0-9]", " "), false, ' ') + "." + CaseUtils.toCamelCase(fieldType.replaceAll("d365_", "").replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim() + "." + CaseUtils.toCamelCase(fieldName.replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim();
        if (fieldName.contains(":")) {
            String[] fieldNameSplit = fieldName.trim().split(":");
            getBundle().setProperty("loc.auto.fieldName",fieldNameSplit[0].trim());
            getBundle().setProperty("loc.auto.fieldValue",fieldNameSplit[1].trim());
            fieldNamePrint = fieldNameSplit[0].trim() + " : " + fieldNameSplit[1].trim();
        } else {
            getBundle().setProperty("loc.auto.fieldName",fieldName.trim());
            getBundle().setProperty("loc.auto.fieldValue","");
            fieldNamePrint = fieldName;
        }
        if (fieldInstance != null && !fieldInstance.isBlank() && !fieldInstance.isEmpty()) {
            getBundle().setProperty("loc.auto.fieldInstance",fieldInstance);
        } else {
            getBundle().setProperty("loc.auto.fieldInstance","1");
        }
        forValue(fieldName,fieldType);
        String locVal = getBundle().getPropertyValue(locator);
        String locPatternName = patternCode + ".patternLoc." + fieldType;
        String locPatternVal = getBundle().getPropertyValue(locPatternName);
        if (locPatternVal.equals(locPatternName) || locPatternVal.length() < 5) {
            System.out.println("=====>[ERROR] => [Locator Pattern '"+ locPatternName + "' not available]");
        } else {
            if (locator.equals(locVal) || locVal.length() < 5) {
                locator = "auto." + locator;
                getBundle().setProperty(locator,"{\"locator\":["+locPatternVal+"],\"desc\":\""+fieldNamePrint + " : ["+fieldType+"] Field \"}");
            }
        }
        // Locator Logging
        if (getBundle().getPropertyValue("loc.pattern.log").trim().equalsIgnoreCase("true")) {
            System.out.println("==== AUTO GENERATED: LOCATOR (Pattern) ====> " + locator.replace("auto.","") + "=" + getBundle().getPropertyValue(locator));
        }
//        BrowserGlobal.iScrollToAnElement(locator);
        return locator;
    }


    private static void forValue(String argFieldName, String argFieldType) {
        if (!argFieldType.toLowerCase().contains("checkbox") && !argFieldType.toLowerCase().contains("switch")) {
//            String locator;
            String patternCode = getBundle().getPropertyValue("loc.pattern.code");
            String locLabelPatternName = patternCode+".patternLoc.label";
            String locLabelPatternVal = getBundle().getPropertyValue(locLabelPatternName);
            String forValue;
            if (locLabelPatternVal.equals(locLabelPatternName) || locLabelPatternVal.length() < 5) {
                System.out.println("=====>[ERROR] => [Locator Pattern '"+ locLabelPatternName + "' not available]");
            } else {
                String locLabelValue = "{\"locator\":["+locLabelPatternVal+"],\"desc\":\""+argFieldName+" : [LABEL] Field \"}";
                getBundle().setProperty("loc.auto.label",locLabelValue);
                try {
                    BrowserGlobal.iWaitUntilElementPresentWithTimeout("loc.auto.label","3");
                    forValue = $("loc.auto.label").getAttribute("for");
                    getBundle().setProperty("loc.auto.forValue",forValue);
                } catch (Exception e) {

                }
                // Locator Logging
                if (getBundle().getPropertyValue("loc.pattern.log").trim().equalsIgnoreCase("true")) {
                    System.out.println("==== AUTO GENERATED: LOCATOR (Pattern) ====> " + "loc.auto.label" + "=" + getBundle().getPropertyValue("loc.auto.label"));
                }
            }
        }

    }
}