package com.cts.justdial.bddrunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "com.cts.justdial.stepdefinition", // Step definition package
        tags="@Smoke",
        plugin = {
                "pretty", // Prints console logs
                "html:test-output/smoke-cucumber-reports/smoke_cucumber.html", // Optional cucumber HTML report
                "json:test-output/smoke-cucumber-reports/smoke_cucumber.json",  // JSON report for extent adapter
                "junit:test-output/smoke-cucumber-reports/smoke_cucumber.xml",
        },
        monochrome = true// Clean console output
)
public class SmokeCucumberRunner extends AbstractTestNGCucumberTests {

}
