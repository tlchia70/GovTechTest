package com.ahq.pages;

import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.util.Reporter;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qmetry.qaf.automation.core.MessageTypes.Fail;
import static com.qmetry.qaf.automation.core.MessageTypes.Pass;

public class LaunchAndLogin {

    /**
     * @param url [dashboard url]
     */
    @QAFTestStep(description="I Launch Website {0}")
    @And("I Launch Website {string}")
    public void launchWebSite(String url) throws Exception {
        web.openBrowserAndMaximise_Web(url);
        web.iWaitForPageToLoad_Web();
        web.waitForSecs_Web("3");
    }

    @QAFTestStep(description="Validate Webpage Loaded")
    @And("Validate Webpage Loaded")
    public void validateWebPageLoaded() throws Exception {
        try {
            // locator for confirmation text
            String xpath = "//div[@role='banner' and @class='css-iosdev']//h1[@class='chakra-text css-1alksd9' and text()='Technical Test Form']";

            // Get WebDriver instance from WebDriverTestBase
            WebDriver driver = new WebDriverTestBase().getDriver();

            // Locate the input field
            WebElement WebPage_Text = driver.findElement(By.xpath(xpath));

            //Reporter.log("First Name Error message: " + FirstName_input_field_Error.getText(),Fail);
            Reporter.logWithScreenShot("WebPage Header Text: " + WebPage_Text.getText(), Pass);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If the element is not found, execute the "else" block
            //Reporter.log("First Name Error message does not exist.",Info);
            Reporter.logWithScreenShot("WebPage Header Text does not exist.",Fail);
            // Perform actions for when the error is not present
        }
    }


}
