package com.cts.justdial.bddrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "com.cts.justdial.stepdefinition", // Step definition package
        plugin = {
                "pretty", // Prints console logs
                "html:target/cucumber-reports/cucumber.html", // Optional cucumber HTML report
                "json:target/cucumber-reports/cucumber.json",  // JSON report for extent adapter
                "testng:target/cucumber-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // Extent report adapter
        },
        monochrome = true, // Clean console output
        dryRun = false
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
        // Nothing else needed, Extent is configured via adapter
}
