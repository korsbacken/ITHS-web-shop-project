Feature: Checkout page of The Shop web app
  This feature includes tests to verify functionality of The Shop's checkout page

  Scenario: Remove an item from the shopping cart
    Given User has navigated to the product page
    When User clicks on the "Add to cart" button
    And User navigates to the checkout page
    And User removes 1 item
    Then The item count in the shopping cart should decrease by 1

  Scenario: Add multiple items to the shopping cart
    Given Users has navigated to the product page
    When User clicks on the "Add to cart" button 5 times
    And Users navigates to the checkout page
    Then The item count in the shopping cart should be 5

  Scenario Outline: User should see same product on checkout page as was added from the shop page
    Given User has navigated to the product page
    When User adds a specific product to the cart <addedProduct>
    And User has navigated to the checkout page
    Then User should see same product on checkout page <actualProduct>
    Examples:
      | addedProduct                                                                  | actualProduct                                                                |
      | "Mens Casual Premium Slim Fit T-Shirts"                                       | "Mens Casual Premium Slim Fit T-Shirts"                                      |
      | "Pierced Owl Rose Gold Plated Stainless Steel Double"                         | "Pierced Owl Rose Gold Plated Stainless Steel Double"                        |
      | "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s"                         | "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s"                        |
      | "Opna Women's Short Sleeve Moisture"                                          | "Opna Womens Short Sleeve Moisture"                                          |

  Scenario: User should see correct total price on checkout page when adding multiple products
    Given User has navigated to the product page
    When User adds some sample products to cart
    And User navigates to the checkout page
    Then User should see correct total price

  Scenario: User should be able to add multiple products to the cart
    Given Users has navigated to the product page
    When User added multiple products to the cart
    Then Products should be added to cart

  Scenario: User should be able to move in and out from the checkout page
    Given Users has navigated to the product page
    When User clicks on the checkout button
    Then User should be navigated to checkout page
    When User clicks on shop button
    Then User should be navigated to shopping page

  Scenario: User should be able to add same products multiple times to the cart
    Given Users has navigated to the product page
    When User add to cart button three times for the same product
    Then three pieces of same products should be added e cart

  Scenario: User should be able to select different type of payment methods on checkout page
    Given Users has navigated to the product page
    When User navigates to the checkout page
    Then Credit card option should be selected as a default payment method
    And User should be able select debit cart or paypal as well as a payment methods

