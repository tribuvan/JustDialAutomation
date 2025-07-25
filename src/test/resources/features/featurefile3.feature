Feature: Gyms Feature

  Background:
    Given User is on the Justdial Home Page

  @Smoke @Regression
  Scenario: TC_03_01 - Validate Gyms Option
    Given I load test data for "TC_03_01"
    When User navigates to the Gyms section
    And User clicks fitness option button
    Then Validate that Gyms option is clickable

  @Regression
  Scenario: TC_03_02 - Validate Gym Page Title
    Given I load test data for "TC_03_02"
    When User sets location to Hyderabad
    And User navigates to the Gyms section
    Then Verify Gyms page title

  @Regression
  Scenario: TC_03_03 - Fetch All Gym Names and Save to Excel
    Given I load test data for "TC_03_03"
    When User sets location to Hyderabad
    And User navigates to the Gyms section
    Then Fetch all the Gym names and save them in an Excel file
