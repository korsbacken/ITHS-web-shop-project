Feature: Shop page of The Shop web app
  This feature includes tests to verify functionality of the shop page of The Shop's web app

  Background: The user has navigated to the shop page
    Given User has navigated to the start page
    When The user clicks on the shop page
    Then The user is navigated to the shop page

  Scenario: User should be able to add product to cart by clicking add to cart button
    When user clicks on Add to cart button
    Then product should be added to the cart

  Scenario: User should be able navigate checkout page by clicking checkout button
    When user clicks on checkout button
    Then user should be landed to checkout page

  Scenario: User filters products by mens clothing
    When user clicks on the mens clothing from shop page
    Then Only mens clothing products must be visible

  Scenario: User filters products by women's clothing
    When user clicks on the women's clothing from shop page
    Then Only women's clothing products must be visible

  Scenario: User filters products by jewelery
    When user clicks on the jewelery from shop page
    Then Only jewelery products must be visible

  Scenario: User filters products by electronics
    When User clicks on the electronics from shop page
    Then Only electronic products must be visible

  Scenario: User removes the filter
    When User clicks on the all from shop page
    Then Only all products must be visible

  Scenario Outline: User searches with different keywords
    When User searches with specific word in the search field <keyword>
    Then Relevant results are displayed
    And Results contain the keyword <keyword>
    Examples:
      | keyword       |
      | "Mens casual" |
      | "GOLD"        |
      | "women"       |
      | "6"           |

  Scenario: When user adds X amount of products to the cart the total amount inside checkout button should increase
    When adding 3 products to the cart
    Then number of products in the cart should be 3

