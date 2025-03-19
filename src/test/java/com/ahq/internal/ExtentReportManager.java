package com.ahq.internal;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.ahq.listeners.StepListener.threadedExtentTestNodes;
import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;


public class ExtentReportManager {
    
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    private static ExtentReports getExtentReport() throws IOException {

        if (extent == null) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");
            String formattedDateTime = currentDateTime.format(formatter);
            extent = new ExtentReports();
            String environment = (getBundle().getString("env.code") != null) ? getBundle().getString("env.code")
                                                                                     .toUpperCase() : "env.code";
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/email-report.html");

//            ExtentSparkReporter spark = new ExtentSparkReporter("extent-reports/" +
//                    formattedDateTime +
//                    "-TestReport-" +
//                    environment +
//                    getBundle().getString("pipelineJobSuffix") +
//                    ".html");

//            ExtentSparkReporter spark = new ExtentSparkReporter("extent-reports/" +
//                                                                formattedDateTime +
//                                                                "-TestReport-" +
//                                                                environment +
//                                                                getBundle().getString("pipelineJobSuffix") +
//                                                                ".html");
//            spark.loadXMLConfig(new File("config/spark-config.xml"));
            spark.config().setDocumentTitle(getBundle().getPropertyValue("exec.report.title"));
            spark.config().setReportName(getBundle().getPropertyValue("exec.report.name"));
            spark.config().setJs("(function() {const collection = document.getElementsByClassName(\"logo\"); collection[0].setAttribute(\"style\",\"width:50px; height:50px; background-image: url('data:image/png;base64, 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')\"); })();");
            spark.config().thumbnailForBase64(true);

//            spark.config().
            extent.attachReporter(spark);
        }
        return extent;
    }
    
    public static void createTest(String testName) throws IOException {
        test.set(getExtentReport().createTest(testName));
    }
    
    public static ExtentTest getTest() {
        return test.get();
    }
    
    public static ExtentTest getCurrentTestNode() {
        return threadedExtentTestNodes.get().get(threadedExtentTestNodes.get().size() - 1);
    }
    
    public static void flushReports() throws IOException {
        getExtentReport().flush();
    }
    
    public static void removeTest(ExtentTest testToRemove) throws IOException {
        getExtentReport().removeTest(testToRemove);
        test.remove();
    }
}
