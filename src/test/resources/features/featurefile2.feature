Feature: Free Listing Feature

  Background:
    Given User is on the Justdial Home Page

  Scenario: TC_02_01 - Open Free Listing Page
    Given I load test data for "TC_02_01"
    When User clicks on Free Listing option
    Then Verify Free Listing page title

  Scenario: TC_02_02 - Validate Empty Input Error Message
    Given I load test data for "TC_02_02"
    When User clicks on Free Listing option
    Then Validate error message for empty input is "Please Enter a Mobile Number"

  Scenario: TC_02_03 - Validate Alphabetic Input Error Message
    Given I load test data for "TC_02_03"
    When User clicks on Free Listing option
    Then Validate error message for alphabetic input is "Please Enter a Mobile Number"

  Scenario: TC_02_04 - Validate Special Character Input Error Message
    Given I load test data for "TC_02_04"
    When User clicks on Free Listing option
    Then Validate error message for special character input is "Please Enter a Mobile Number"

  Scenario: TC_02_05 - Validate Short Number Input Error Message
    Given I load test data for "TC_02_05"
    When User clicks on Free Listing option
    Then Validate error message for short number input is "Please Enter a Valid Mobile Number"

  Scenario: TC_02_06 - Validate OTP Popup For Long Number Input
    Given I load test data for "TC_02_06"
    When User clicks on Free Listing option
    Then Validate OTP popup is displayed for long number input
