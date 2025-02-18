Feature: Purchase a product

  Scenario: Purchase a product
    Given I log in to Amazon
    And I search for "Iphone"
    When I add the product to the cart
    Then I proceed to checkout
