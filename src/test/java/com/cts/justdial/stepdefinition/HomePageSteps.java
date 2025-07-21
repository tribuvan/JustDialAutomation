package com.cts.justdial.stepdefinition;

import com.cts.justdial.fwutils.ExcelDataProvider;
import com.cts.justdial.pageobjects.HomePage;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.Map;

public class HomePageSteps {

    HomePage homePage = Hooks.homePage;

    @Given("User is on the Justdial Home Page")
    public void user_is_on_the_justdial_home_page() {
          homePage.closePopup(Hooks.driver);
    }

    Map<String, String> testData;

    @Given("I load test data for {string}")
    public void i_load_test_data(String tcId) {
        TestDataContext.setTcId(tcId);
        testData= ExcelDataProvider.getTestData(tcId);
        TestDataContext.setTestData(testData);
    }


    @Then("Verify the home page title")
    public void verify_home_page_title() {
        String expectedTitle = testData.get("Test Data1");
        String actualTitle = homePage.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Home page title does not match");
    }

    @When("User sets location to Hyderabad")
    public void user_sets_location() {
        String expectedCity = testData.get("Test Data1");
        homePage.setLocation(Hooks.driver, expectedCity);
    }

    @Then("Verify selected location is Hyderabad")
    public void verify_selected_location() {
        String expectedLocation = testData.get("Test Data1");
        String selectedLocation = homePage.getSelectedLocation(Hooks.driver);
        Assert.assertEquals(selectedLocation, expectedLocation, "Selected location does not match");
    }

    @When("User searches for service Car Wash Services")
    public void user_searches_for_service() {
        String service = testData.get("Test Data2");
        homePage.searchService(Hooks.driver, service);
    }

    @When("User clicks on Free Listing option")
    public void user_clicks_on_free_listing_option() {
        Hooks.freeListingPage.clickFreeListing(Hooks.driver);
    }

    @When("User navigates to the Gyms section")
    public void user_navigates_to_gyms_section() {
        homePage.clickFitnessOption(Hooks.driver);
        homePage.selectGym(Hooks.driver);
    }
}
