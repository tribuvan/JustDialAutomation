package com.cts.justdial.stepdefinition;

import com.cts.justdial.browserutils.BrowserFactory;
import com.cts.justdial.fwutils.PropertiesFileReader;
import com.cts.justdial.pageobjects.*;
import com.cts.justdial.seleniumutils.ScreenshotUtil;
import com.cts.justdial.seleniumutils.Waits;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    public static WebDriver driver;
    public static HomePage homePage;
    public static CarWashServicesPage carWashPage;
    public static FreeListingPage freeListingPage;
    public static GymsPage gymsPage;

    @Before
    public void setUp() throws Exception {

        String bn = null;
        String url = null;
        String wr = null;
        String hip = null;

        try {
            bn = PropertiesFileReader.getPropertyValue("config", "browsername");
            url = PropertiesFileReader.getPropertyValue("config", "url");
            wr = PropertiesFileReader.getPropertyValue("config", "wheretorun");
            hip = PropertiesFileReader.getPropertyValue("config", "hubIP");

            driver = BrowserFactory.getBrowser(bn, url, wr, hip);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        homePage = new HomePage(driver);
        carWashPage = new CarWashServicesPage(driver);
        freeListingPage = new FreeListingPage(driver);
        gymsPage = new GymsPage(driver);


        driver.manage().deleteAllCookies();
        Waits.implicitlyWait(driver, 60);

    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                // Capture screenshot on failure
                ScreenshotUtil.takeScreenShot(driver,scenario.getName());
            }
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
