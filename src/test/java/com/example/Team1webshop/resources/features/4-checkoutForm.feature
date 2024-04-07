Feature: Checkout page form of The Shop web app
  This feature includes test to verify the form functionality on the checkout page

  Background: User is on the checkout page with a product added to the cart
    Given user is on the products page
    And add product to cart
    And click on checkout

  Scenario Outline: User should get an error message when submitting an incomplete form on the checkout page
    Given user fills in the form with data <firstName> <lastName> <email> <address> <country> <city> <zipCode> <ccName> <ccNumber> <expDate> <cvv>
    When user click on continue to checkout
    Then user should get an error message
    Examples:
      | firstName | lastName   | email            | address            | country   | city    | zipCode | ccName          | ccNumber   | expDate   | cvv   |
      | ""        | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | ""         | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | ""               | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | "test@email.com" | ""                 | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | ""        | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | ""      | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | ""      | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | ""              | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | ""         | "2030/01" | "123" |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | ""        | "123" |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | ""    |

  Scenario Outline: User should be notified on entering invalid mail id
    Given user fills in the form with invalid email <firstName> <lastName> <email> <address> <country> <city> <zipCode> <ccName> <ccNumber> <expDate> <cvv>
    When user click on continue to checkout
    Then user should be notified with an error message
    Examples:
      | firstName | lastName   | email             | address            | country   | city    | zipCode | ccName          | ccNumber   | expDate   | cvv   |
      | "test"    | "testsson" | "test@.com"       | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | "test@email."     | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | "test_email.com"  | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
      | "test"    | "testsson" | "test@@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |

  Scenario Outline: User should be able to proceed credit card payment
    Given user fills in the form with data <firstName> <lastName> <email> <address> <country> <city> <zipCode> <ccName> <ccNumber> <expDate> <cvv>
    When User select debit card as a payment method
    And user clicks on continue to checkout
    Then User should be able proceed credit card payment
    Examples:
      | firstName | lastName   | email            | address            | country   | city    | zipCode | ccName          | ccNumber   | expDate   | cvv   |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |

  Scenario Outline: User should be able to proceed paypal payment
    Given user fills in the form with data <firstName> <lastName> <email> <address> <country> <city> <zipCode> <ccName> <ccNumber> <expDate> <cvv>
    When User select paypal as a payment method
    And user clicks on continue to checkout
    Then User should be able proceed paypal payment
    Examples:
      | firstName | lastName   | email            | address            | country   | city    | zipCode | ccName          | ccNumber   | expDate   | cvv   |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |

  Scenario Outline: User should be able to proceed credit card payment
    Given user fills in the form with data <firstName> <lastName> <email> <address> <country> <city> <zipCode> <ccName> <ccNumber> <expDate> <cvv>
    When user clicks on continue to checkout
    Then User should be able proceed credit card payment
    Examples:
      | firstName | lastName   | email            | address            | country   | city    | zipCode | ccName          | ccNumber   | expDate   | cvv   |
      | "test"    | "testsson" | "test@email.com" | "Jungmansgatan 12" | "Svergie" | "Malmö" | "21111" | "Test Testsson" | "12345678" | "2030/01" | "123" |
