package com.cts.justdial.stepdefinition;

import com.cts.justdial.pageobjects.FreeListingPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class FreeListingSteps {

    FreeListingPage freeListingPage = Hooks.freeListingPage;

    @Then("Verify Free Listing page title")
    public void verify_free_listing_page_title() {
        String actualTitle = freeListingPage.getTitle();
        String expectedTitle=TestDataContext.getTestData().get("Test Data1");
        Assert.assertEquals(actualTitle,expectedTitle,"Title not matching");
    }

    @Then("Validate error message for empty input is {string}")
    public void validate_error_message_for_empty_input(String expectedMessage) {
        String actualMessage = freeListingPage.checkErrorMsgWithEmptyInput(Hooks.driver);
        expectedMessage="Please Enter a Mobile Number";
        Assert.assertEquals(actualMessage, expectedMessage,"Message Matched");
    }

    @Then("Validate error message for alphabetic input is {string}")
    public void validate_error_for_alphabetic_input( String expectedMessage) {
        String input=TestDataContext.getTestData().get("Test Data1");
        expectedMessage="Please Enter a Mobile Number";
        String actualMessage = freeListingPage.checkErrorMsgWithAlphabeticInput(Hooks.driver, input);
        Assert.assertEquals(actualMessage, expectedMessage,"Message MisMatched");
    }

    @Then("Validate error message for special character input is {string}")
    public void validate_error_for_special_character_input(String expectedMessage) {
        String input = TestDataContext.getTestData().get("Test Data1");
        expectedMessage="Please Enter a Mobile Number";
        String actualMessage = freeListingPage.checkErrorMsgWithSpecialCharInput(Hooks.driver, input);
        Assert.assertEquals(actualMessage,expectedMessage,"Message Mismatched" );
    }

    @Then("Validate error message for short number input is {string}")
    public void validate_error_for_short_number_input(String expectedMessage) {
        String input=TestDataContext.getTestData().get("Test Data1");
        expectedMessage="Please Enter a Valid Mobile Number";
        String actualMessage = freeListingPage.checkErrorMsgWithShortNumberInput(Hooks.driver, input);
        Assert.assertEquals(actualMessage, expectedMessage,"Message  mismatched");
    }

    @Then("Validate OTP popup is displayed for long number input")
    public void validate_otp_popup_for_long_number() {
        String input=TestDataContext.getTestData().get("Test Data1");
        boolean otpDisplayed = freeListingPage.checkErrorMsgWithLongNumInput(Hooks.driver, input);
        Assert.assertTrue(otpDisplayed, "OTP popup not displayed for long number");
    }
}
