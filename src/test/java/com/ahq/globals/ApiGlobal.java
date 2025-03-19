package com.ahq.globals;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.qmetry.qaf.automation.step.WsStep;
import com.qmetry.qaf.automation.step.CommonStep.*;
import static com.qmetry.qaf.automation.util.Validator.*;
import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.sun.jersey.api.client.ClientResponse;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.And;

public class ApiGlobal {
    private static String tmpApiResBody;
    public static ClientResponse tmpApiResponse;

// api request {0}
// api request {0} with data {1}
// api assert response status code is {0}
// api assert response status is {0}
// api download response body as file {0}
// api download response header as file {0}
// api store response json path {0} value into variable {1}

    /**
     * @param argRequest
     */
    @QAFTestStep(description = "I request api {0}")
    @And("I request api {string}")
    public static void iRequestApi(String argRequest) {
//        Object tmpRequest = argRequest;
        tmpApiResponse = WsStep.userRequests(argRequest);
//        InputStream tmpInputStream = tmpApiResponse.getEntityInputStream();
//        try (Stream<String> lines = new BufferedReader(new InputStreamReader(tmpInputStream)).lines()) {
//            tmpApiResBody = lines.collect(Collectors.joining("\n"));
//            System.out.println(argRequest + " == API Response =====>  "  + tmpApiResBody);
//        }
    }


    /**
     * @param request
     * @param data
     */
    @QAFTestStep(description = "I request api {0} with data {1}")
    public static void iRequestApiWithData(String request, Map<String, Object> data) {
        Object tmpRequest = request;
        new WsStep();
        tmpApiResponse = WsStep.userRequests(tmpRequest, data);
        InputStream tmpInputStream = tmpApiResponse.getEntityInputStream();
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(tmpInputStream)).lines()) {
            tmpApiResBody = lines.collect(Collectors.joining("\n"));
        }
    }

    /**
     * @param filename
     */
    @QAFTestStep(description = "I download api response body as file {file-name}")
    public void iDownloadApiResponseBodyAsFile(String filename) {
        try {
            String tmpDirectory = "resources/downloads/";
            File directory = new File(tmpDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }

            InputStream in = tmpApiResponse.getEntityInputStream();
            File tmpFile = new File(tmpDirectory, filename);
            Files.copy(in, tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**

     * @param filename
     */
    @QAFTestStep(description = "I download api response header as file {file-name}")
    public void iDownloadApiResponseHeaderAsFile(String filename) {
        try {
            String tmpDirectory = "resources/downloads/";
            File directory = new File(tmpDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter tmpFile = new FileWriter(tmpDirectory + filename);
            tmpFile.write(tmpApiResponse.getHeaders().toString());
            tmpFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename
     */
    @QAFTestStep(description = "I download api response body and header as file {file-name}")
    public void iDownloadApiResponseBodyAndHeaderAsFile(String filename) {

    }

    /**
     * @param jpath
     * @param varName
     */
    @QAFTestStep(description = "I store api response json path {jpath} value into variable {var-name}")
    public static void iStoreApiResponseJsonPathValueToVariable(String jpath, String varName) {
        if (!jpath.startsWith("$"))
            jpath = "$." + jpath;
        Object value = JsonPath.read(tmpApiResBody, jpath);
        getBundle().setProperty(varName, value);
        // WsStep.storeResponseBodyto(jpath, varName);
    }

    /**
     * @param xpath
     * @param varName
     */
    @QAFTestStep(description = "I store api response xml path {xpath} value into variable {var-name}")
    public static void iStoreApiResponseXmlPathValueToVariable(String xpath, String varName) {
        WsStep.sayValueAtXPath(varName, xpath);
    }

    /**
     * @param headerKey
     * @param varName
     */
    @QAFTestStep(description = "I store api response header key {header-key} value into variable {var-name}")
    public static void iStoreApiResponseHeaderKeyValueToVariable(String headerKey, String varName) {
        getBundle().setProperty(varName, tmpApiResponse.getHeaders().get(headerKey));
    }

    /**
     * @param statusCode
     */
    @QAFTestStep(description = "I assert api response status code is {0}")
    public static void iAssertApiResponseStatusCode(String statusCode) {
        WsStep.responseShouldHaveStatusCode(Integer.parseInt(statusCode));
    }

    /**
     * @param status
     */
    @QAFTestStep(description = "I assert api response status is {0}")
    @And("I assert api response status is {string}")
    public static void iAssertApiResponseStatus(String status) {
        WsStep.responseShouldHaveStatus(status);
    }

    /**
     * @param header
     */
    @QAFTestStep(description = "I assert api response has header {header-key}")
    public static void iAssertApiResponseHasHeader(String header) {
        WsStep.responseShouldHaveHeader(header);
    }

    /**
     * @param header
     * @param value
     */
    @QAFTestStep(description = "I assert api response has header {header-key} with value {value}")
    public static void iAssertApiResponseHasHeaderWithValue(String header, String value) {
        WsStep.responseShouldHaveHeaderWithValue(header, value);
    }

    /**
     * QMETRY-API-13
     *
     * @param jsonpath
     */
    @QAFTestStep(description = "I assert api response has json path {path}")
    public static void iAssertApiResponseHaveJsonpath(String jsonpath) {
        WsStep.responseShouldHaveJsonPath(jsonpath);
    }

    /**
     * QMETRY-API-14
     *
     * @param jsonpath
     */
    @QAFTestStep(description = "I assert api response not have given json path {path}")
    public static void iAssertApiResponseNotHaveJsonpath(String jsonpath) {
        WsStep.responseShouldNotHaveJsonPath(jsonpath);
    }

    /**
     * QMETRY-API-15
     *
     * @param expectedvalue
     * @param jpath
     */
    @QAFTestStep(description = "I assert api response value is {value} in json path {jpath}")
    public static void iAssertApiResponseHaveJsonPathValue(String expectedvalue, String jpath) {
        // WsStep.responseShouldHaveKeyWithValue(tmpExpectedvalue, jpath);
        String value = JsonPath.read(tmpApiResBody, getJpath(jpath)).toString();
        assertTrue(value.contains(expectedvalue), "given value is not present in json path",
                "given value is present in json path");
    }

    /**
     * QMETRY-API-16
     *
     * @param value
     * @param jpath
     */
    @QAFTestStep(description = "I assert api response value is not {value} in json path {jpath}")
    public static void iAssertApiResponseNotHaveJsonPathValue(String expectedvalue, String jpath) {
        // WsStep.responseShouldNotHaveValue(tmpExpectedvalue, jpath);
        String value = JsonPath.read(tmpApiResBody, getJpath(jpath));
        assertTrue(!value.contains(expectedvalue), "given value is present in json path",
                "given value is not present in json path");
    }

    /**
     * QMETRY-API-17
     *
     * @param expectedvalue
     * @param jpath
     */
    @QAFTestStep(description = "I assert api response value is less than {expectedvalue} in json path {jpath}")
    public static void iAssertApiResponseHaveLessThanValue(String expectedvalue, String jpath) {
        // WsStep.responseShouldLessThan(expectedvalue, jpath);
        String value = JsonPath.read(tmpApiResBody, getJpath(jpath));
        assertTrue(Double.parseDouble(value) < Double.parseDouble(expectedvalue),
                "given value is not less than response value", "given value is less than response value");

    }

    /**
     * QMETRY-API-18
     *
     * @param expectedvalue
     * @param jpath
     */
    @QAFTestStep(description = "I assert api response value is less than or equals to {expectedvalue} in json path {jpath}")
    public static void iAssertApiResponseHaveLessThanOrEqualToValue(String expectedvalue, String jpath) {
        // WsStep.responseShouldLessThanOrEqualsTo(expectedvalue, jpath);
        String value = JsonPath.read(tmpApiResBody, getJpath(jpath));
        assertTrue(Double.parseDouble(value) <= Double.parseDouble(expectedvalue),
                "given value is not less than or equals to response value",
                "given value is less than or equals to response value");

    }

    /**
     * QMETRY-API-19
     *
     * @param expectedvalue
     * @param jpath
     */
    @QAFTestStep(description = "I assert api response value is greater than {expectedvalue} in json path {jpath}")
    public static void iAssertApiResponseHaveGreaterThanValue(String expectedvalue, String jpath) {
        // WsStep.responseShouldGreaterThan(expectedvalue, jpath);
        String value = JsonPath.read(tmpApiResBody, getJpath(jpath));
        assertTrue(Double.parseDouble(value) > Double.parseDouble(expectedvalue),
                "given value is not greater than response value", "given value is greater than response value");

    }

    /**
     * QMETRY-API-20
     *
     * @param expectedvalue
     * @param jpath
     */
    @QAFTestStep(description = "I assert api response value is greater than or equals to {expectedvalue} in json path {jpath}")
    public static void iAssertApiResponseHaveGreaterThanOrEqualToValue(String expectedvalue, String jpath) {
        // WsStep.responseShouldGreaterThanOrEqualsTo(expectedvalue, jpath);
        String value = JsonPath.read(tmpApiResBody, getJpath(jpath));
        assertTrue(Double.parseDouble(value) >= Double.parseDouble(expectedvalue),
                "given value is not greater than or equals to response value",
                "given value is greater than or equals to response value");

    }

    /**
     * QMETRY-API-21
     *
     * @param expectedvalue
     * @param jpath
     */
    @QAFTestStep(description = "I assert api response value is ignoring case {expectedvalue} in json path {jpath}")
    public static void iAssertApiResponseHaveValueIgnoreCase(String expectedvalue, String jpath) {
        // WsStep.responseShouldHaveValueIgnoringCase(expectedvalue, jpath);
        String value = JsonPath.read(tmpApiResBody, getJpath(jpath));
        assertTrue(value.equals(expectedvalue), "given text is not present in response",
                "given text is present in response");
    }

    public static String getJpath(String jpath) {
        if (!jpath.startsWith("$"))
            jpath = "$." + jpath;
        return jpath;
    }

    /**
     * QMETRY-API-22
     *
     * @param expectedvalue
     * @param jpath
     */
    @QAFTestStep(description = "I assert api response value contains ignoring case {expectedvalue} in json path {jpath}")
    public static void iAssertApiResponseValueContainsIgnoreCase(String expectedvalue, String jpath) {
        // WsStep.responseShouldHaveValueContainsIgnoringCase(expectedvalue, jpath);
        String value = JsonPath.read(tmpApiResBody, getJpath(jpath));
        assertTrue(value.contains(expectedvalue), "given text is not present in response",
                "given text is present in response");
    }

    /**
     * QMETRY-API-23
     *
     * @param schema
     */
    @QAFTestStep(description = "I verify api response schema with {0}")
    public static void iVerifyApiResponseSchema(String schema) {
        WsStep.verifyResponseSchema(schema);
    }

    /**
     * QMETRY-API-24
     *
     * @param expectedvalue
     * @param jpath
     */
    @QAFTestStep(description = "I verify api response value is {value} in json path {jpath}")
    public static void iVerifyApiResponseHaveJsonPathValue(String expectedvalue, String jpath) {
        WsStep.responseShouldHaveValueAtJsonpath(expectedvalue, jpath);
    }

    /**
     * QMETRY-API-25
     *
     * @param expectedvalue
     * @param jpath
     */
    @QAFTestStep(description = "I verify api response value is not {value} in json path {jpath}")
    public static void iVerifyApiResponseNotHaveJsonPathValue(String expectedvalue, String jpath) {
        WsStep.responseShouldNotHaveValueAtJsonpath(expectedvalue, jpath);
    }

    /**
     * QMETRY-API-26
     *
     * @param statusCode
     */
    @QAFTestStep(description = "I check api response status code is {0}")
    public static void iCheckApiResponseStatusCode(String statusCode) {
        WsStep.responseShouldHaveStatusCode(Integer.parseInt(statusCode));
    }

    /**
     * QMETRY-API-27
     *
     * @param status
     */
    @QAFTestStep(description = "I check api response status is {0}")
    public static void iCheckApiResponseStatus(String status) {
        WsStep.responseShouldHaveStatus(status);
    }

    /**
     * QMETRY-API-28
     *
     * @param expectedvalue
     * @param xpath
     */
    @QAFTestStep(description = "I check api response should have value {expectedvalue} at xpath {xpath}")
    public static void iCheckApiResponseShouldHaveXpathValue(String expectedvalue, String xpath) {
        Object tmpExpectedvalue = expectedvalue;
        WsStep.responseShouldHaveValueAtXpath(tmpExpectedvalue, xpath);
    }

    /**
     * QMETRY-API-29
     *
     * @param xpath
     */
    @QAFTestStep(description = "I check api response should not have xpath {xpath}")
    public static void iCheckApiResponseShouldNotHaveXpath(String xpath) {
        WsStep.responseShouldNotHaveXpath(xpath);
    }

    /**
     * QMETRY-API-30
     *
     * @param xpath
     */
    @QAFTestStep(description = "I check api response should have xpath {xpath}")
    public static void iCheckApiResponseShouldHaveXpath(String xpath) {
        WsStep.responseShouldHaveXpath(xpath);
    }

    /**
     * QMETRY-API-31
     *
     * @param jsonpath
     */
    @QAFTestStep(description = "I check api response should have json path {jsonpath}")
    public static void iCheckApiResponseShouldHaveJsonpath(String jsonpath) {
        WsStep.responseShouldHaveJsonPath(jsonpath);
    }

    /**
     * QMETRY-API-32
     *
     * @param jsonpath
     */
    @QAFTestStep(description = "I check api response should not have json path {jsonpath}")
    public static void iCheckApiResponseShouldNotHaveJsonpath(String jsonpath) {
        WsStep.responseShouldNotHaveJsonPath(jsonpath);
    }

    /**
     * QMETRY-API-33
     *
     * @param header
     * @param headerValue
     */
    @QAFTestStep(description = "I check api response should have header {header} with value {headerValue}")
    public static void iCheckApiResponseShouldHaveHeaderValue(String header, String headerValue) {
        WsStep.responseShouldHaveHeaderWithValue(header, headerValue);
    }

    /**
     * QMETRY-API-34
     *
     * @param expectedvalue
     * @param jsonPath
     */
    @QAFTestStep(description = "I check api response should have value {expectedvalue} at json path {jsonPath}")
    public static void iCheckApiResponseShouldHaveJsonPathValue(String expectedvalue, String jsonPath) {
        Object tmpExpectedvalue = expectedvalue;
        WsStep.responseShouldHaveKeyWithValue(tmpExpectedvalue, jsonPath);
    }

    /**
     * QMETRY-API-35
     *
     * @param expectedvalue
     * @param jsonPath
     */
    @QAFTestStep(description = "I check api response contains {expectedvalue} at json path {jsonPath}")
    public static void iCheckApiResponseContainsJsonPathValue(String expectedvalue, String jsonPath) {
        WsStep.responseShouldHaveKeyAndValueContains(expectedvalue, jsonPath);
    }


}
