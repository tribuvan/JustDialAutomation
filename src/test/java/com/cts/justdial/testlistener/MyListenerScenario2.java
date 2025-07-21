package com.cts.justdial.testlistener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cts.justdial.seleniumutils.ScreenshotUtil;
import com.cts.justdial.testrunners.Scenario1Runner;
import com.cts.justdial.testrunners.Scenario2Runner;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class MyListenerScenario2 implements ITestListener {
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        String reportPath = "test-output/ThirdPartyReports/ExtentReportScenario2.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Just Dial Suite");
        spark.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Team4");
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
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = Scenario2Runner.driver;
        String path = ScreenshotUtil.takeScreenShot(driver, result.getName());
        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.addScreenCaptureFromPath(path);
        System.out.println("Screenshot saved at: " + path);
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