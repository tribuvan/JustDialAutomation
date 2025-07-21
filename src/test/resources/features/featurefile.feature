Feature: Car Wash Services Feature

  Background:
    Given User is on the Justdial Home Page

  Scenario: TC_01_01 - Validate Home Page Title
    Given I load test data for "TC_01_01"
    Then Verify the home page title

  Scenario: TC_01_02 - Validate Current Location
    Given I load test data for "TC_01_02"
    When User sets location to Hyderabad
    Then Verify selected location is Hyderabad

  Scenario: TC_01_03 - Validate Car Wash Services Page Title
    Given I load test data for "TC_01_03"
    When User sets location to Hyderabad
    And User searches for service Car Wash Services
    Then Verify the car wash page title

  Scenario: TC_01_04 - Validate if All Filters is Enabled
    Given I load test data for "TC_01_04"
    When User sets location to Hyderabad
    And User searches for service Car Wash Services
    Then Verify that All Filters option is enabled

  Scenario: TC_01_05 - Validate Filter Box is Displayed
    Given I load test data for "TC_01_05"
    When User sets location to Hyderabad
    And User searches for service Car Wash Services
    Then Verify filter box is displayed

  Scenario: TC_01_06 - Validate Top Rated Filter
    Given I load test data for "TC_01_06"
    When User sets location to Hyderabad
    And User searches for service Car Wash Services
    And User clicks filter button
    And User selects Top Rated filter button
    Then Verify Top Rated filter can be applied

  Scenario: TC_01_07 - Validate Rating Filter
    Given I load test data for "TC_01_07"
    When User sets location to Hyderabad
    And User searches for service Car Wash Services
    And  User clicks filter button
    And User selects 4.0+ Rating filter button
    Then Verify 4.0+ rating filter can be applied

  Scenario: TC_01_08 - Validate Listing Size Greater Than 5
    Given I load test data for "TC_01_08"
    When User sets location to Hyderabad
    And User searches for service Car Wash Services
    And User clicks filter button
    And User selects Top Rated filter button
    And User selects 4.0+ Rating filter button
    Then Verify there are more than 5 Car Wash Service listings

  Scenario: TC_01_09 - Validate Votes Greater Than 20
    Given I load test data for "TC_01_09"
    When User sets location to Hyderabad
    And User searches for service Car Wash Services
    And User clicks filter button
    And User selects Top Rated filter button
    And User selects 4.0+ Rating filter button
    And User selects All Filter button
    Then Verify all listings have votes greater than 20

  Scenario: TC_01_10 - Validate Rating Greater Than 4
    Given I load test data for "TC_01_10"
    When User sets location to Hyderabad
    And User searches for service Car Wash Services
    And User clicks filter button
    And User selects Top Rated filter button
    And User selects 4.0+ Rating filter button
    And User selects All Filter button
    Then Verify all listings have ratings greater than 4

  Scenario: TC_01_11 - Validate Getting Top 5 Car Wash Contacts
    Given I load test data for "TC_01_11"
    When User sets location to Hyderabad
    And User searches for service Car Wash Services
    And User clicks filter button
    And User selects Top Rated filter button
    And User selects 4.0+ Rating filter button
    And User selects All Filter button
    Then Fetch top 5 car wash service contacts and save to Excel
