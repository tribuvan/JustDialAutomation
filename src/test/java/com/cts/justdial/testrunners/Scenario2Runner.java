package com.cts.justdial.testrunners;

import com.cts.justdial.browserutils.BrowserFactory;
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
import com.cts.justdial.testlistener.MyListenerScenario2;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MyListenerCombined.class)
public class Scenario2Runner {
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
            fo = new FreeListingPage(driver);
            driver.manage().deleteAllCookies();
            Waits.implicitlyWait(driver, 120);


        } catch (Exception e) {
            throw e;
        }
    }

    @Test(priority = 0, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void openFreeListingPage(String title,String rowNo){
        try {
            ho.closePopup(driver);
            fo.clickFreeListing(driver);
            Assert.assertEquals(fo.getTitle(), title);
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"openFreeListingPage");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 1, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateEmptyInput(String rowNo){
        try {
            ho.closePopup(driver);
            fo.clickFreeListing(driver);
            Assert.assertEquals(fo.checkErrorMsgWithEmptyInput(driver), "Please Enter a Mobile Number");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateEmptyInput");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 2, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateAplhabeticInput(String str,String rowNo){
        try {
            ho.closePopup(driver);
            fo.clickFreeListing(driver);
            Assert.assertEquals(fo.checkErrorMsgWithAlphabeticInput(driver, str), "Please Enter a Mobile Number");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateAplhabeticInput");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 3, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateSpecialCharInput(String str,String rowNo){
        try {
            ho.closePopup(driver);
            fo.clickFreeListing(driver);
            Assert.assertEquals(fo.checkErrorMsgWithSpecialCharInput(driver, str), "Please Enter a Mobile Number");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateSpecialCharInput");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 4, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateShortNumberInput(String num,String rowNo){
        try {
            ho.closePopup(driver);
            fo.clickFreeListing(driver);
            Assert.assertEquals(fo.checkErrorMsgWithShortNumberInput(driver, num), "Please Enter a Valid Mobile Number");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateShortNumberInput");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 5, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateLongNumberInput(String num,String rowNo){
        try {
            ho.closePopup(driver);
            fo.clickFreeListing(driver);
            Assert.assertTrue(fo.checkErrorMsgWithLongNumInput(driver, num), "OTP Popup is not displayed");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateLongNumberInput");
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
