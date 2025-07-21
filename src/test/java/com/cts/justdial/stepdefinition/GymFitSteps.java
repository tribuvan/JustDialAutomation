package com.cts.justdial.stepdefinition;

import com.cts.justdial.fwutils.CommonUtils;
import com.cts.justdial.pageobjects.GymsPage;
import com.cts.justdial.fwutils.ExcelUtil;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class GymFitSteps {

    GymsPage gymsPage = Hooks.gymsPage;

    @When("User clicks fitness option button")
    public void user_clicks_fitness_option_button()
    {
        Hooks.homePage.clickFitnessOption(Hooks.driver);
    }

    @Then("Validate that Gyms option is clickable")
    public void validate_gyms_option_clickable() {

        Assert.assertTrue(Hooks.homePage.isGymsEnabled(Hooks.driver), "Gyms option not clickable");
    }

    @Then("Verify Gyms page title")
    public void verify_gyms_page_title() {
        CommonUtils.sureWait(3);
        String actualTitle = Hooks.gymsPage.getTitle();
        String expectedTitle=TestDataContext.getTestData().get("Test Data2");
        Assert.assertEquals(actualTitle, expectedTitle,"Title not matching");
    }

    @Then("Fetch all the Gym names and save them in an Excel file")
    public void fetch_all_gym_names_and_save_to_excel() {
        List<WebElement> gymsList = gymsPage.getSubMenuItems(Hooks.driver);
        ExcelUtil.writeListToExcel(gymsList, "Gyms", 0, "Scenario3_GymsList.xlsx");
        Assert.assertTrue(gymsList.size() > 0, "No gyms found");
    }
}
