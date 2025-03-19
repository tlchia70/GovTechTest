package com.ahq.pages;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import io.cucumber.java.en.And;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;

import static com.ahq.globals.web.getPageName;

public class commonStep {


    /**
     * @param optionText [dropdown option]
     @param dropdownLabel [dropdown label]
     */
    @QAFTestStep(description="I select {0} from {1} dropdown")
    @And("I select {string} from {string} dropdown")
    public static void selectDropdownOption(String optionText, String dropdownLabel) throws Exception {
        optionText = optionText.trim();
        web.iWaitForPageToLoad_Web();
        web.waitForSecs_Web("3");
        WebDriver driver = new WebDriverTestBase().getDriver();

        // Locate the dropdown element based on the label and scroll to it
        WebElement dropdownElement = driver.findElement(By.xpath("//div[text()='" + dropdownLabel + "']/following-sibling::div//div[contains(@class, 'dropdown__control')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
        Thread.sleep(500); // Small delay to ensure the element is fully in view

        // Click on the dropdown to reveal the options
        dropdownElement.click();
        Thread.sleep(500); // Small delay to allow the dropdown menu to appear

        // Locate the container of all options and retrieve its text
        WebElement optionsContainer = driver.findElement(By.xpath("//div[contains(@class, 'dropdown__menu')]"));
        String allOptionsText = optionsContainer.getText().trim();

        // Split the options text by line breaks to create a list of individual options
        List<String> optionsList = List.of(allOptionsText.split("\\r?\\n")); // Split by newline

        // Check if the option is in the list and click it if found
        boolean optionFound = false;
        for (String itemText : optionsList) {
            itemText = itemText.trim();
            System.out.println("Found dropdown item: " + itemText); // Debugging for each option individually

            // Check if the item text matches the user's input
            if (itemText.equalsIgnoreCase(optionText)) {
                // Find the element with this text and click it
                WebElement optionToClick = driver.findElement(By.xpath("//div[contains(@class, 'dropdown__menu')]//div[text()='" + itemText + "']"));
//                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionToClick); // Scroll to the element
                Thread.sleep(300); // Wait for scrolling
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionToClick); // Click using JavaScript
                System.out.println("Clicked on dropdown item: " + optionText); // Confirmation
                optionFound = true;
                break;
            }
        }

        // If no matching option is found, throw an exception
        if (!optionFound) {
            throw new Exception("Option '" + optionText + "' not found in the " + dropdownLabel + " dropdown");
        }
    }

    /* Tech Test   */
    @QAFTestStep(description="I select {0}")
    @And("I select {string}")
    public static void selectDropdownOption_Single(String optionText) throws Exception {

        optionText = optionText.trim();
        web.iWaitForPageToLoad_Web();
        web.waitForSecs_Web("3");
        WebDriver driver = new WebDriverTestBase().getDriver();

        // Locate the dropdown element based on the label and scroll to it
        WebElement dropdownElement = driver.findElement(By.xpath("//input[@role='combobox' and @id='6453551e00efba001225204b']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
        Thread.sleep(500); // Small delay to ensure the element is fully in view

        // Click on the dropdown to reveal the options
        dropdownElement.click();
        Thread.sleep(500); // Small delay to allow the dropdown menu to appear

        // Wait for the options to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@role='combobox']")));

        // Input the value read from test data file into combo-box
        web.inputText_Web(optionText, "6453551e00efba001225204b");

    }


    @QAFTestStep(description="I scroll to {0} button and click")
    @And("I scroll to {string} button and click")
    public void scrollToButtonClick(String field) throws Exception {
        BrowserGlobal.iScrollToTheBottomOfThePage();
        web.clickButton_Web(field);
        web.iWaitForPageToLoad_Web();
        web.waitForSecs_Web("3");
    }

    @QAFTestStep(description = "I select the radio button with label {0} under {1}")
    @And("I select the radio button with label {string} under {string}")
    public static void selectRadioButtonByLabel(String optionText, String RadioGroup) throws Exception {
        WebDriver driver = new WebDriverTestBase().getDriver();

        // XPath to locate the containing div label and the radio button based on the provided question and option text
       String xpath = "//div[contains(@aria-label, '" + RadioGroup + "') ]//span[@class='chakra-radio__label css-6cahl3' and text()='" + optionText + "']";

        // Find the radio button label element using the constructed XPath
        WebElement radioLabel = driver.findElement(By.xpath(xpath));

        // Scroll the radio button label into view if necessary
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", radioLabel);
//        Thread.sleep(500); // Small delay to ensure the element is fully in view

        // Click on the radio button label to select it
        radioLabel.click();
    }

    @QAFTestStep(description = "I click button with label {0}")
    @And("I click button with label {string}")
    public static void clickFormButtonByText(String text) throws InterruptedException {
        WebDriver driver = new WebDriverTestBase().getDriver();

        // XPath to locate the clickable element by a partial match of its text
        //String xpath = "//span[@class='clickable-element' and contains(text(), '" + text + "')]";
        String xpath = "//button[@type='button' and @class='chakra-button css-kqovcr' and contains(text(), '" + text + "')]";

        try {
            // Wait for the element to be clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Scroll into view and click the radio button
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", radioButton);
            Thread.sleep(500); // Small delay to ensure the element is fully in view
            radioButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("Radio button with text '" + text + "' not found.");
            throw e; // Rethrow the exception to fail the test if element is not found
        } catch (Exception e) {
            System.out.println("Error occurred while trying to click the radio button: " + e.getMessage());
            throw e;
        }
    }



}
