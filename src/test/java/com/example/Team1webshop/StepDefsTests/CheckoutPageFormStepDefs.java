package com.example.Team1webshop.StepDefsTests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckoutPageFormStepDefs {

    private final WebDriver driver;

    public CheckoutPageFormStepDefs() {
        this.driver = Hooks.getDriver();
    }

    // Samuel
    @Given("user is on the products page")
    public void user_is_on_products_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products");
    }

    // Samuel
    @Given("add product to cart")
    public void add_product_to_cart() {
        driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]")).click();
    }

    // Samuel
    @Given("click on checkout")
    public void click_on_checkout() {
        driver.findElement(By.xpath("//a[contains(@class, 'btn-warning') and contains(text(), 'Checkout')]")).click();
    }

    // Samuel
    @Given("user fills in the form with data {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void user_fills_in_the_form(String firstName, String lastName, String email, String address, String country, String city, String zipCode, String ccName, String ccNumber, String expDate, String cvv) {
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("address")).sendKeys(address);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zip")).sendKeys(zipCode);
        driver.findElement(By.id("cc-name")).sendKeys(ccName);
        driver.findElement(By.id("cc-number")).sendKeys(ccNumber);
        driver.findElement(By.id("cc-expiration")).sendKeys(expDate);
        driver.findElement(By.id("cc-cvv")).sendKeys(cvv);
    }

    // Samuel
    @When("user click on continue to checkout")
    public void user_click_on_continue_to_checkout() {
        driver.findElement(By.xpath("//button[contains(text(), 'Continue to checkout')]")).click();
    }

    // Samuel
    @Then("user should get an error message")
    public void user_should_get_an_error_message() {
        boolean isErrorMessageDisplayed = false;
        List<WebElement> formFeedbackElements = driver.findElements(By.className("invalid-feedback"));
        for (int i = 0; i < formFeedbackElements.size(); i++) {
            WebElement element = formFeedbackElements.get(i);
            String feedbackText = element.getText().trim();
            if (!feedbackText.isEmpty()) {
                System.out.println("Error message for element #" + (i + 1) + " has the text: " + feedbackText);
                isErrorMessageDisplayed = true;
                break;
            }
        }
        Assertions.assertTrue(isErrorMessageDisplayed, "No error message is being displayed for required text field");
    }
    //divya
    @Given("user fills in the form with invalid email {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void user_fills_in_the_form_with_invalid_email(String firstName, String lastName, String email, String address, String country, String city, String zipCode, String ccName, String ccNumber, String expDate, String cvv) {
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("address")).sendKeys(address);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zip")).sendKeys(zipCode);
        driver.findElement(By.id("cc-name")).sendKeys(ccName);
        driver.findElement(By.id("cc-number")).sendKeys(ccNumber);
        driver.findElement(By.id("cc-expiration")).sendKeys(expDate);
        driver.findElement(By.id("cc-cvv")).sendKeys(cvv);
    }
    // Divya - Samuel
    @Then("user should be notified with an error message")
    public void error_message_display() {
        String error_message = "";
        List<WebElement> formFeedbackElements = driver.findElements(By.className("invalid-feedback"));
        for (WebElement element : formFeedbackElements) {
            if (!element.getText().isEmpty()) {
                error_message = element.getText();
                break;
            }
        }
        Assertions.assertEquals("Please enter a valid email address for shipping updates.", error_message);
    }
    // Semih
    @When("User select debit card as a payment method")
    public void user_select_debit_card_as_a_payment_method() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@type='radio'])[2]")).click();
    }


    // Semih
    @When("user clicks on continue to checkout")
    public void user_clicks_on_continue_to_checkout() throws InterruptedException {
        WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(), 'Continue to checkout')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", checkoutButton);
        checkoutButton.click();
    }

    // Semih
    @When("User select paypal as a payment method")
    public void user_select_paypal_as_a_payment_method() {
        driver.findElement(By.xpath("(//input[@type='radio'])[3]")).click();
    }

    // Semih
    @Then("User should be able proceed paypal payment")
    public void user_should_be_able_proceed_paypal_payment() {
        String expectedUrl = "https://webshop-agil-testautomatiserare.netlify.app/checkout?paymentMethod=paypal";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }
    // Semih
    @Then("User should be able proceed credit card payment")
    public void user_should_be_able_proceed_credit_card_payment() {
        String expectedUrl = "https://webshop-agil-testautomatiserare.netlify.app/checkout?paymentMethod=on";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }
    // Semih
    @Then("User should be able proceed debit card payment")
    public void user_should_be_able_proceed_debit_card_payment() {
        String expectedUrl = "https://webshop-agil-testautomatiserare.netlify.app/checkout?paymentMethod=on";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }
}
