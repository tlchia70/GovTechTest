package com.ahq.pages;

import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.util.Reporter;
import io.cucumber.java.en.And;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import static com.qmetry.qaf.automation.core.MessageTypes.Fail;


public class fileUpload {

    // Magic numbers for common file types
    private static final byte[] PDF_MAGIC_NUMBER = { 0x25, 0x50, 0x44, 0x46 }; // %PDF
    private static final byte[] JPG_MAGIC_NUMBER = { (byte) 0xFF, (byte) 0xD8, (byte) 0xFF }; // ÿØÿ
    private static final byte[] PNG_MAGIC_NUMBER = { (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A }; // ‰PNG


    @QAFTestStep(description = "I upload the file {0}")
    @And("I upload the file {string}")
    public static void uploadFileBasedOnLabel(String filename) throws Exception {

        // Call the file upload function with the resolved uploader label
        fileUploader(filename);
    }

    public static String validateFileType(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] fileBytes = new byte[8]; // Read the first 8 bytes
        fis.read(fileBytes);
        fis.close();

        if (startsWith(fileBytes, PDF_MAGIC_NUMBER)) {
            return "PDF";
        } else if (startsWith(fileBytes, JPG_MAGIC_NUMBER)) {
            return "JPG";
        } else if (startsWith(fileBytes, PNG_MAGIC_NUMBER)) {
            return "PNG";
        } else {
            return "Unknown file type";
        }
    }

    private static boolean startsWith(byte[] fileBytes, byte[] magicNumber) {
        if (fileBytes.length < magicNumber.length) {
            return false;
        }
        for (int i = 0; i < magicNumber.length; i++) {
            if (fileBytes[i] != magicNumber[i]) {
                return false;
            }
        }
        return true;
    }

    private static void fileUploader(String filename) throws Exception {
        WebDriver driver = new WebDriverTestBase().getDriver();

        // Define the relative file path
        String relativeFilePath = "resources/data/"+filename;
        // Construct the full file path based on the relative path
        String filePath = System.getProperty("user.dir") + "/" + relativeFilePath;

        // XPath to locate the file input based on the uploader label
        //String xpath = "//div[@data-testid='file-uploader-wrapper' and .//span[@data-testid='title' and text()='" + uploaderLabel + "']]//input[@type='file']";
        String xpath = "//input[@id='645354f093ceeb0012cc628a']";


        try {
            // Wait for the file input to be present
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

            // Scroll the element into view if necessary
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fileInput);
            Thread.sleep(500); // Small delay to ensure the element is fully in view

            // check that file type is PDF
            String fileType = validateFileType(filePath);
            if (fileType == "PDF") {

                // Upload the file by setting the file path in the input element
                fileInput.sendKeys(filePath);

            } else {
                Reporter.logWithScreenShot("Invalid File Type: " + fileType, Fail);
            }

        } catch (NoSuchElementException e) {
            System.out.println("Element not found with XPath: " + xpath);
            throw e; // Rethrow the exception to fail the test
        }
    }

}
