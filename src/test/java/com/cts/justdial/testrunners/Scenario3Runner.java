package com.cts.justdial.testrunners;

import com.cts.justdial.browserutils.BrowserFactory;
import com.cts.justdial.fwutils.CommonUtils;
import com.cts.justdial.fwutils.ExcelDataProvider;
import com.cts.justdial.fwutils.ExcelUtil;
import com.cts.justdial.fwutils.PropertiesFileReader;
import com.cts.justdial.pageobjects.CarWashServicesPage;
import com.cts.justdial.pageobjects.FreeListingPage;
import com.cts.justdial.pageobjects.GymsPage;
import com.cts.justdial.pageobjects.HomePage;
import com.cts.justdial.seleniumutils.ScreenshotUtil;
import com.cts.justdial.seleniumutils.Waits;
import com.cts.justdial.testlistener.MyListener;
import com.cts.justdial.testlistener.MyListenerCombined;
import com.cts.justdial.testlistener.MyListenerScenario3;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;

@Listeners(MyListenerCombined.class)
public class Scenario3Runner {
    public static WebDriver driver = null;
    HomePage ho=null;
    CarWashServicesPage co = null;
    FreeListingPage fo = null;
    GymsPage go = null;
    @BeforeMethod
    public void perSetup() throws Exception{
        String bn = null;
        String url = null;
        String wr = null;
        String ip = null;
        try {
            bn = PropertiesFileReader.getPropertyValue("config", "browsername");
            url = PropertiesFileReader.getPropertyValue("config", "url");
            wr = PropertiesFileReader.getPropertyValue("config", "wheretorun");
            ip = PropertiesFileReader.getPropertyValue("config", "hubip");
            driver = BrowserFactory.getBrowser(bn,url,wr,ip);
            ho = new HomePage(driver);
            go = new GymsPage(driver);
            driver.manage().deleteAllCookies();
            Waits.implicitlyWait(driver, 120);

        } catch (Exception e) {
            throw e;
        }
    }

    @Test(priority = 0, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateGymsOption(String rowNo){
        try {
            ho.closePopup(driver);
            ho.clickFitnessOption(driver);
            Assert.assertTrue(ho.isGymsEnabled(driver), "Gyms Option is not clickable");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateGymsOption");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 1, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateGymPageTitle(String location, String pageTitle,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.clickFitnessOption(driver);
            ho.selectGym(driver);
            CommonUtils.sureWait(3);
            Assert.assertEquals(go.getTitle(), pageTitle);
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateGymPageTitle");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 2, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateGyms(String location,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.clickFitnessOption(driver);
            ho.selectGym(driver);
            List<WebElement> gymName = go.getSubMenuItems(driver);
            ExcelUtil.writeListToExcel(gymName, "Gyms", 0, "Scenario3_GymsList.xlsx");
            Assert.assertTrue(gymName.size() > 0, "No Gyms are Displayed");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateGyms");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }
    @AfterMethod
    public void terminate(){
        driver.close();
        driver.quit();
    }
}
