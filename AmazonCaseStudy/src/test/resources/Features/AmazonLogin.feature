Feature: Amazon Login

  Scenario: User logs into Amazon
    Given User is on Login Page
    When User enter valid credentials
    And Clicks on Login button
    Then User navigated to home page
    