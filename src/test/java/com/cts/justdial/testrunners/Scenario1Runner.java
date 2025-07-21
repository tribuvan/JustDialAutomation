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
import com.cts.justdial.testlistener.MyListenerScenario1;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.Map;

@Listeners(MyListenerCombined.class)
public class Scenario1Runner {

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
            co = new CarWashServicesPage(driver);
            driver.manage().deleteAllCookies();
            Waits.implicitlyWait(driver, 120);

        } catch (Exception e) {
            throw e;
        }
    }

    @Test(priority = 0, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateHomePageTitle(String title,String rowNo){
        try {
            ho.closePopup(driver);
            Assert.assertEquals(ho.getTitle(), title);
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateHomePageTitle");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 1, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateLocation(String currentLocation,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, currentLocation);
            String location = ho.getSelectedLocation(driver);
            Assert.assertEquals(location, currentLocation, "Location is not matching");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateLocation");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 2, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validatePageTitle(String location, String searchText,String pageTitle,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.searchService(driver, searchText);
            CommonUtils.sureWait(3);
            String actualTitle = co.getTitle();
            Assert.assertEquals(actualTitle, pageTitle);
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validatePageTitle");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 3, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateIfAllFiltersIsEnabled(String location, String searchText,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.searchService(driver, searchText);
            Assert.assertTrue(co.isAllFilterEnabled(driver), "All Filters is not selected after applying filters");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateIfAllFiltersIsEnabled");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 4, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateFilterBox(String location, String searchText,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.searchService(driver, searchText);
            co.clickFilterBtn(driver);
            Assert.assertTrue(co.isFilterBoxDisplayed(driver), "Filter Box is not displayed");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateFilterBox");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }
    @Test(priority = 5, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateTopRatedFilter(String location, String searchText,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.searchService(driver, searchText);
            co.clickFilterBtn(driver);
            co.filterTopRated(driver);
            Assert.assertTrue(co.isTopRatedSelected(driver), "Top Rated is not being selected");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateTopRatedFilter");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 6, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateRatingFilter(String location, String searchText,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.searchService(driver, searchText);
            co.clickFilterBtn(driver);
            co.filterRating(driver);
            Assert.assertTrue(co.isRatingSelected(driver), "4.0+ is not being selected");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateRatingFilter");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }


    @Test(priority = 7, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateListingSize(String location, String searchText,String num,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.searchService(driver, searchText);
            co.clickFilterBtn(driver);
            co.filterTopRated(driver);
            co.filterRating(driver);
            co.applyFilters(driver);
            Assert.assertTrue(co.listingSize(driver) > Integer.parseInt(num), "There are less than 5 Car Was Service listings");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateListingSize");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 8, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateVotes(String location, String searchText,String minVote,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.searchService(driver, searchText);
            co.clickFilterBtn(driver);
            co.filterTopRated(driver);
            co.filterRating(driver);
            co.applyFilters(driver);
            Assert.assertTrue(co.votesGreaterThan(driver, Integer.parseInt(minVote)));
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateVotes");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 9, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateRating(String location, String searchText,String rating,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.searchService(driver, searchText);
            co.clickFilterBtn(driver);
            co.filterTopRated(driver);
            co.filterRating(driver);
            co.applyFilters(driver);
            Assert.assertTrue(co.ratingGreaterThan(driver, Double.parseDouble(rating)));
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateRating");
            ExcelUtil.writeResult("FAIL",Integer.parseInt(rowNo));
            Assert.fail();
        }
    }

    @Test(priority = 10, dataProvider = "carWashTestData",dataProviderClass = ExcelDataProvider.class)
    public void validateGettingTop5Contacts(String location, String searchText,String noOfContacts,String rowNo){
        try {
            ho.closePopup(driver);
            ho.setLocation(driver, location);
            ho.searchService(driver, searchText);
            co.clickFilterBtn(driver);
            co.filterTopRated(driver);
            co.filterRating(driver);
            co.applyFilters(driver);
            Map<String, String> carWashContacts = co.getMatchingServices(driver);
            ExcelUtil.writeMapToExcel(carWashContacts, "Car Wash Name", "Car Wash Contact", 0, 1, "Scenario1_CarWashContacts.xlsx");
            Assert.assertTrue(carWashContacts.size() == Integer.parseInt(noOfContacts), "The number of CarWash Services is not equal to 5");
            ExcelUtil.writeResult("PASS",Integer.parseInt(rowNo));
        }catch (Exception | AssertionError e){
            ScreenshotUtil.takeScreenShot(driver,"validateGettingTop5Contacts");
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


