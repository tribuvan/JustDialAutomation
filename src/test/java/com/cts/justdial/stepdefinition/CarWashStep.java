package com.cts.justdial.stepdefinition;

import com.cts.justdial.fwutils.CommonUtils;
import com.cts.justdial.pageobjects.CarWashServicesPage;
import com.cts.justdial.fwutils.ExcelUtil;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.Map;

public class CarWashStep {

    CarWashServicesPage carWashPage = Hooks.carWashPage;
    @When("User clicks filter button")
    public void user_clicks_filter_button()
    {
        carWashPage.clickFilterBtn(Hooks.driver);
    }
    @When("User selects Top Rated filter button")
    public void user_selects_top_Rated_filter()
    {
        carWashPage.filterTopRated(Hooks.driver);
    }
    @When("User selects 4.0+ Rating filter button")
    public void user_selects_rating_filter()
    {
        carWashPage.filterRating(Hooks.driver);
    }
    @When("User selects All Filter button")
    public void user_selects_all_filter_button()
    {
        carWashPage.applyFilters(Hooks.driver);
    }

    @Then("Verify the car wash page title")
    public void verify_car_wash_page_title() {
        String expectedTitle=TestDataContext.getTestData().get("Test Data3");
        CommonUtils.sureWait(3);
        String actualTitle = carWashPage.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle,"Title not matching");
    }

    @Then("Verify that All Filters option is enabled")
    public void verify_all_filters_enabled() {
        Assert.assertTrue(carWashPage.isAllFilterEnabled(Hooks.driver), "All Filters option is not enabled");
    }

    @Then("Verify filter box is displayed")
    public void verify_filter_box_displayed() {
        carWashPage.clickFilterBtn(Hooks.driver);
        Assert.assertTrue(carWashPage.isFilterBoxDisplayed(Hooks.driver), "Filter box is not displayed");
    }


    @Then("Verify Top Rated filter can be applied")
    public void verify_top_rated_filter_applied() {

        //carWashPage.filterTopRated(Hooks.driver);
        Assert.assertTrue(carWashPage.isTopRatedSelected(Hooks.driver), "Top Rated filter not applied");
    }

    @Then("Verify 4.0+ rating filter can be applied")
    public void verify_rating_filter_applied() {
        //carWashPage.filterRating(Hooks.driver);
        Assert.assertTrue(carWashPage.isRatingSelected(Hooks.driver), "4.0+ filter not applied");
    }

    @Then("Verify there are more than 5 Car Wash Service listings")
    public void verify_listing_size() {
        //carWashPage.applyFilters(Hooks.driver);
        String size = TestDataContext.getTestData().get("Test Data3");
        Assert.assertTrue(carWashPage.listingSize(Hooks.driver) > Integer.parseInt(size), "Less than 5 listings found");
    }

    @Then("Verify all listings have votes greater than 20")
    public void verify_votes_greater_than_20() {
        String votes = TestDataContext.getTestData().get("Test Data3");
        Assert.assertTrue(carWashPage.votesGreaterThan(Hooks.driver, 20), "Listings with <= 20 votes found");
    }

    @Then("Verify all listings have ratings greater than 4")
    public void verify_ratings_greater_than_4() {
        String rating = TestDataContext.getTestData().get("Test Data3");
        Assert.assertTrue(carWashPage.ratingGreaterThan(Hooks.driver, Double.parseDouble(rating)), "Listings with <= 4.0 rating found");
    }

    @Then("Fetch top 5 car wash service contacts and save to Excel")
    public void fetch_and_save_top_5_contacts() {
        Map<String, String> contacts = carWashPage.getMatchingServices(Hooks.driver);
        ExcelUtil.writeMapToExcel(contacts, "Car Wash Name", "Car Wash Contact", 0, 1,
                "Scenario1_CarWashContacts.xlsx");
        Assert.assertEquals(contacts.size(), 5, "Did not fetch exactly 5 contacts");
    }
}
