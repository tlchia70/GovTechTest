package com.ahq.pages;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.util.Reporter;
import io.cucumber.java.en.And;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.interactions.Actions;

import static com.ahq.globals.web.getPageName;
import static com.ahq.pages.commonStep.*;
import static com.qmetry.qaf.automation.core.MessageTypes.*;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class applicationDetails {

    //private static final Logger log = LoggerFactory.getLogger(applicationDetails.class);

// ----------------- Utilities ----------------------------

    @QAFTestStep(description = "Validate Date : {0}")
    @And("Validate Date : {string}")
    public static boolean validateDate(String dateStr) throws Exception {

        // Define the expected date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Attempt to parse the date
            LocalDate input_date = LocalDate.parse(dateStr, formatter);

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Check if the input date is less than the current date
            return input_date.isBefore(currentDate); // Returns true if input_Date is before currentDate and the format is valid

        } catch (DateTimeParseException e) {
            return false; // If parsing fails, the format is invalid
        }



    }


    @QAFTestStep(description = "Fill in First Name : {0}")
    @And("Fill in First Name : {string}")
    public static void fillInFirstName(String FirstName) throws Exception {

        if (FirstName == null) {
            /* Null value read from the input Excel Test Data, would result in error.
            *  Therefor, input of Null value is simulated by clearing the web element text input field
            * */

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Build XPath dynamically to locate the input field based on the label
            String xpath = "//input[@aria-label='1. First Name']";

            // Locate the input field
            WebElement FirstName_input_field = driver.findElement(By.xpath(xpath));

            FirstName_input_field.click();

            FirstName_input_field.clear();

            // Use Actions to click outside the element
            Actions actions = new Actions(driver);

            // Move the mouse to the input field, then offset by (width + 10, 0) to move just outside
            actions.moveToElement(FirstName_input_field, FirstName_input_field.getSize().getWidth() / 2 + 10, 0).click().perform();

        } else {
            web.inputText_Web(FirstName, "1. First Name");
        }
    }


    @QAFTestStep(description = "Validate First Name")
    @And("Validate First Name")
    public static void validateFirstName() throws Exception {

        try {
            // check for error message
            String Error_xpath = "//div[@id='64532b5f00efba00121f117e-feedback']//div[@class='css-1ag8dug' and text()='This field is required']";

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Locate the input field
            WebElement FirstName_input_field_Error = driver.findElement(By.xpath(Error_xpath));

            //Reporter.log("First Name Error message: " + FirstName_input_field_Error.getText(),Fail);
            Reporter.logWithScreenShot("First Name Error message: " + FirstName_input_field_Error.getText(), Fail);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If the element is not found, execute the "else" block
            //Reporter.log("First Name Error message does not exist.",Info);
            Reporter.logWithScreenShot("First Name Error message does not exist.",Pass);
            // Perform actions for when the error is not present
        }

    }



    @QAFTestStep(description = "Fill in Last Name : {0}")
    @And("Fill in Last Name : {string}")
    public static void fillInLastName(String LastName) throws Exception {

        if (LastName == null) {
            /* Null value read from the input Excel Test Data, would result in error.
             *  Therefor, input of Null value is simulated by clearing the web element text input field
             * */

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Build XPath dynamically to locate the input field based on the label
            String xpath = "//input[@aria-label='1. Last Name']";

            // Locate the input field
            WebElement LastName_input_field = driver.findElement(By.xpath(xpath));

            LastName_input_field.click();

            LastName_input_field.clear();

            // Use Actions to click outside the element
            Actions actions = new Actions(driver);

            // Move the mouse to the input field, then offset by (width + 10, 0) to move just outside
            actions.moveToElement(LastName_input_field, LastName_input_field.getSize().getWidth() / 2 + 10, 0).click().perform();
        } else {
            web.inputText_Web(LastName, "2. Last Name");
        }

    }

    @QAFTestStep(description = "Validate Last Name")
    @And("Validate Last Name")
    public static void validateLastName() throws Exception {

        try {
            // check for error message
            String Error_xpath = "//div[@id='64532b696a6af30013dc8321-feedback']//div[@class='css-1ag8dug' and text()='This field is required']";

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Locate the input field
            WebElement LastName_input_field_Error = driver.findElement(By.xpath(Error_xpath));

            Reporter.logWithScreenShot("Last Name Error message: " + LastName_input_field_Error.getText(),Fail);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If the element is not found, execute the "else" block
            Reporter.logWithScreenShot("Last Name Error message does not exist.",Pass);

            // Perform actions for when the error is not present
        }

    }

    @QAFTestStep(description = "Fill in Email : {0}")
    @And("Fill in Email : {string}")
    public static void fillInEmail(String Email) throws Exception {

        if (Email == null) {
            /* Null value read from the input Excel Test Data, would result in error.
             *  Therefor, input of Null value is simulated by clearing the web element text input field
             * */

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Build XPath dynamically to locate the input field based on the label
            String xpath = "//input[@autocomplete='email']";

            // Locate the input field
            WebElement Email_input_field = driver.findElement(By.xpath(xpath));

            Email_input_field.click();

            Email_input_field.clear();

            // Use Actions to click outside the element
            Actions actions = new Actions(driver);

            // Move the mouse to the input field, then offset by (width + 10, 0) to move just outside
            actions.moveToElement(Email_input_field, Email_input_field.getSize().getWidth() / 2 + 10, 0).click().perform();
        } else {

            web.inputText_Web(Email, "email");
        }
    }

    @QAFTestStep(description = "Validate Email")
    @And("Validate Email")
    public static void validateEmail() throws Exception {

        try {
            // check for error message
            String Error_xpath = "//div[@id='6453521e35eb0c00128fa97d-feedback']//div[@class='css-1ag8dug' and text()='This field is required']";

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Locate the input field
            WebElement Email_input_field_Error = driver.findElement(By.xpath(Error_xpath));

            Reporter.logWithScreenShot("Email Error message: " + Email_input_field_Error.getText(),Fail);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If the element is not found, execute the "else" block
            Reporter.logWithScreenShot("Email Error message does not exists.",Pass);

            // Perform actions for when the error is not present
        }

    }



    @QAFTestStep(description = "Fill in Mobile Number : {0}")
    @And("Fill in Mobile Number : {string}")
    public static void fillInMobileNumber(String Mobileno) throws Exception {

        if (Mobileno == null) {
            /* Null value read from the input Excel Test Data, would result in error.
             *  Therefor, input of Null value is simulated by clearing the web element text input field
             * */

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Build XPath dynamically to locate the input field based on the label
            String xpath = "//input[@autocomplete='tel']";

            // Locate the input field
            WebElement Tel_input_field = driver.findElement(By.xpath(xpath));

            Tel_input_field.click();

            Tel_input_field.clear();

            // Use Actions to click outside the element
            Actions actions = new Actions(driver);

            // Move the mouse to the input field, then offset by (width + 10, 0) to move just outside
            actions.moveToElement(Tel_input_field, Tel_input_field.getSize().getWidth() / 2 + 10, 0).click().perform();
        } else {

            web.inputText_Web(Mobileno, "tel");
        }
    }


    @QAFTestStep(description = "Validate Mobile No")
    @And("Validate Mobile No")
    public static void validateMobileNo() throws Exception {

        try {
            // check for error message
            String Error_xpath = "//div[@id='6453527335eb0c00128fbabf-feedback']//div[@class='css-1ag8dug' and text()='This field is required']";

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Locate the input field
            WebElement MobileNo_input_field_Error = driver.findElement(By.xpath(Error_xpath));

            Reporter.logWithScreenShot("Mobile Number Error message: " + MobileNo_input_field_Error.getText(),Fail);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If the element is not found, execute the "else" block
            Reporter.logWithScreenShot("Mobile Number Error message does not exists.",Pass);

            // Perform actions for when the error is not present
        }

    }


    @QAFTestStep(description = "Select {0} in DatePicker")
    @And("Select {string} in DatePicker")
    public static void datePickerSelect(String date) throws Exception {

        // Get WebDriver instance from WebDriverTestBase
        WebDriver driver = new WebDriverTestBase().getDriver();
        driver.findElement(By.id("645352c16dc31e001202f56f")).click();
        driver.findElement(By.id("645352c16dc31e001202f56f")).sendKeys(date);

    }

    @QAFTestStep(description = "Fill in Date of Birth : {0}")
    @And("Fill in Date of Birth : {string}")
    public static void fillInDOB(String dob) throws Exception {

        if (dob == null) {
            /* Null value read from the input Excel Test Data, would result in error.
             *  Therefor, input of Null value is simulated by clearing the web element text input field
             * */
            WebDriver driver = new WebDriverTestBase().getDriver();
            driver.findElement(By.id("645352c16dc31e001202f56f")).click();
            driver.findElement(By.id("645352c16dc31e001202f56f")).clear();

            // Use Actions to click outside the element
            Actions actions = new Actions(driver);

            // Move the mouse to the input field, then offset by (width + 10, 0) to move just outside
            actions.moveToElement(driver.findElement(By.id("645352c16dc31e001202f56f")), driver.findElement(By.id("645352c16dc31e001202f56f")).getSize().getWidth() / 2 + 10, 0).click().perform();
        } else {
//            if (validateDate(dob)) {
//                Reporter.logWithScreenShot("Date of Birth is valid.", Pass);
//                datePickerSelect(dob);
//            } else {
//                Reporter.logWithScreenShot("Date of Birth is invalid.", Fail);
//            }
                datePickerSelect(dob);
        }

    }

    @QAFTestStep(description = "Validate Date of Birth : {0}")
    @And("Validate Date of Birth : {string}")
    public static void validateDateOfBirth(String Dateinput) throws Exception {

        try {
            // Validation Check 1 : Check for NULL DOB input

            // check for error message
            String Error_xpath = "//div[@id='645352c16dc31e001202f56f-feedback']//div[@class='css-1ag8dug' and text()='This field is required']";

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Locate the input field
            WebElement DOB_input_field_Error = driver.findElement(By.xpath(Error_xpath));

            Reporter.logWithScreenShot("Missing Date of Birth Error message: " + DOB_input_field_Error.getText(), Fail);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Validation Check 2 : Check format is correct and before current date
            if (validateDate(Dateinput)) {
                Reporter.logWithScreenShot("Date of Birth is valid.", Pass);
            } else {
                Reporter.logWithScreenShot("Date of Birth format is invalid or greater than current date.", Fail);
            }
            //Reporter.logWithScreenShot("Missing Date of Birth Error message do not exist.", Pass);

        }

    }

    @QAFTestStep(description = "Fill in Hobbies : {0}")
    @And("Fill in Hobbies : {string}")
    public static void fillInHobbies(String Hobbies) throws Exception {

        // Input string with words separated by commas
        String input = Hobbies;

        // Trim any leading or trailing whitespace and split the string by commas
        String[] words = input.trim().split(",");

        // Iterate through the array and print each word
        for (String word : words) {
            web.clickText_Web(word);

        }


    }



    @QAFTestStep(description = "Select Location : {0}")
    @And("Select Location : {string}")
    public static void selectLocation(String Location) throws Exception {

        commonStep.selectDropdownOption_Single(Location);

    }

    @QAFTestStep(description = "Fill in Address : {0}")
    @And("Fill in Address : {string}")
    public static void fillInAddress(String Address) throws Exception {

        web.inputText_Web(Address, "10. Address");

    }

    @QAFTestStep(description = "I Close Web Browser")
    @And("I Close Web Browser")
    public static void closeWebBrowser() throws Exception {

       BrowserGlobal.iCloseWebBrowser();

    }

}
