package com.cts.justdial.testlistener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cts.justdial.fwutils.CommonUtils;
import com.cts.justdial.seleniumutils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.lang.reflect.Field;

public class MyListener implements ITestListener {
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        String reportPath = "test-output/reports/ExtentReport_"+ CommonUtils.getCurrentDate()+".html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Just Dial Suite");
        spark.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "CodeBreakers");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test != null) {
            test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName());
        }
        try {
            Object testClassInstance = result.getInstance();
            Field driverField = testClassInstance.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            WebDriver driver = (WebDriver) driverField.get(testClassInstance);
            String path = ScreenshotUtil.takeScreenShot(driver, result.getName());
            test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
            test.log(Status.FAIL, result.getThrowable());
            test.addScreenCaptureFromPath(path);
            System.out.println("Screenshot saved at: " + path);
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to take screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}