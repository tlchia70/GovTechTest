package com.ahq.globals;

import com.ahq.globals.utilities.*;
import com.qmetry.qaf.automation.step.QAFTestStep;
import io.cucumber.java.en.And;

import static com.qmetry.qaf.automation.step.CommonStep.switchToParentFrame;

public class Utils {
    /**
     * Switches the webdriver context to the parent frame
     * : I switch to parent window
     * [Note: Returns value]
     */
    @QAFTestStep(description = "Utils: Generate Singapore NRIC with type {0} and age {1} into variable {2}")
    @And("Utils: Generate Singapore NRIC with type {string} and age {string} into variable {string}")
    public static void generateSingaporeNricToVar_Utils(String type, String age, String varName) {
        String NRIC = Utils.nric_singapore_generate(type, age);
        BrowserGlobal.iStoreValueIntoVariable(NRIC, varName);
    }

    @QAFTestStep(description = "Utils: Encrypt password {0} and print")
    @And("Utils: Encrypt password {string} and print")
    public static void encryptPasswordAndPrint_Utils(String password) throws Exception {
        BrowserGlobal.iComment("== Encrypted Password is: " + password_encrypt(password));
    }

    @QAFTestStep(description = "Utils: Generate date from current date by plus or minus days {0} into variable {1}")
    @And("Utils: Generate date from current date by plus or minus days {string} into variable {string}")
    public static void generateDateFromCurrentDateByPlusOrMinusDays_Utils(String days, String varName) throws Exception {
        String dateGenerated = dateTime_currentTimeWithFormatPlusAdditionalDays("dd/mm/yyyy",Integer.parseInt(days));
        BrowserGlobal.iStoreValueIntoVariable(dateGenerated, varName);
    }

    @QAFTestStep(description = "Utils: Generate date from current date by plus or minus years {0} into variable {1}")
    @And("Utils: Generate date from current date by plus or minus years {string} into variable {string}")
    public static void generateDateFromCurrentDateByPlusOrMinusYears_Utils(String years, String varName) throws Exception {
        String dateGenerated = dateTime_currentTimeWithFormatPlusAdditionalYears("d/m/yyyy",Integer.parseInt(years));
        BrowserGlobal.iStoreValueIntoVariable(dateGenerated, varName);
    }

    public static String nric_singapore_generate(String type, String age) { return UtilNricSingapore.generate(type,Integer.parseInt(age)); }
//    public static String nric_singapore_validate(String nric) { return UtilNricSingapore.validate(nric); } // TO BE DEVELOPED
    public static String password_encrypt(String encryptedPassword) { return UtilPassword.encrypt(encryptedPassword);}
    public static String password_decrypt(String password) { return UtilPassword.decrypt(password);}
    public static String password_check(String password) { return UtilPassword.check(password);}
    public static String base64_decrypt(String base64String) { return UtilBase64.decrypt(base64String);}

    //Date & Time Utils
    public static String dateTime_currentDate() { return UtilDateTime.currentDate();}
    public static String dateTime_currentDatePlusAdditionalDays(int numberOfAdditionalDays) { return UtilDateTime.currentDatePlusAdditionalDays(numberOfAdditionalDays);}
    public static String dateTime_currentTimeWithFormatPlusAdditionalDays(String timeFormat, int numberOfAdditionalDays) { return UtilDateTime.currentTimeWithFormatPlusAdditionalDays(timeFormat,numberOfAdditionalDays);}
    public static String dateTime_currentTimeWithFormatPlusAdditionalYears(String timeFormat, int numberOfAdditionalYears) { return UtilDateTime.currentTimeWithFormatPlusAdditionalYears(timeFormat,numberOfAdditionalYears);}

    public static String dateTime_currentDateTime() { return UtilDateTime.currentDateTime();}
    public static String dateTime_currentDateWithFormat(String dateFormat) { return UtilDateTime.currentDateWithFormat(dateFormat);}
    public static String dateTime_currentTimeWithFormat(String timeFormat) { return UtilDateTime.currentTimeWithFormat(timeFormat);}

}
