package com.cts.justdial.bddrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "com.cts.justdial.stepdefinition", // Step definition package
        tags = "@Regression",
        plugin = {
                "pretty", // Prints console logs
                "html:test-output/regression-cucumber-reports/regression_cucumber.html", // Optional cucumber HTML report
                "json:test-output/regression-cucumber-reports/regression_cucumber.json",  // JSON report for extent adapter
                "junit:test-output/regression-cucumber-reports/regression_cucumber.xml",
        },
        monochrome = true// Clean console output
)
public class RegressionCucumberRunner extends AbstractTestNGCucumberTests {
}
