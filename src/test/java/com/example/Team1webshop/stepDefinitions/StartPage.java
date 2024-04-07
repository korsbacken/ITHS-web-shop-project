package com.example.Team1webshop.stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage {

    private final WebDriver driver;

    public StartPage() {
        this.driver = Hooks.getDriver();
    }

    @Given("User has navigated to the start page")
    public void user_has_navigated_to_the_start_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be_webbutiken(String pageTitle) {
        Assertions.assertEquals(pageTitle, driver.getTitle());
    }

    @Then("the heading should be {string}")
    public void the_heading_should_be(String heading) {
        WebElement mainHeading = driver.findElement(By.tagName("h2"));
        String actualText = mainHeading.getText();
        Assertions.assertEquals(heading, actualText);
    }

    @Then("the subtext should be {string}")
    public void the_subtext_should_be(String subtext) {
        WebElement mainHeading = driver.findElement(By.tagName("p"));
        String actualText = mainHeading.getText();
        Assertions.assertEquals(subtext, actualText);
    }

    @When("the user clicks on the shop link {string}")
    public void the_user_clicks_on_the_shop_link(String shopLink) {
        driver.findElement(By.linkText(shopLink)).click();
    }

    @Then("the user should be redirected to the products page {string}")
    public void the_user_should_be_redirected_to_the_products_page(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }

    @When("the user clicks on the checkout link {string}")
    public void the_user_clicks_on_the_checkout_link(String CheckoutLink) {
        driver.findElement(By.linkText(CheckoutLink)).click();
    }

    @Then("the user should be redirected to the checkout page {string}")
    public void the_user_should_be_redirected_to_the_checkout_page(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }

    @When("the user click on the about link on the top of the page")
    public void the_user_click_on_the_about_link_on_the_top_of_the_page() {
        driver.findElement(By.xpath("(//a[@class='nav-link px-2 text-white' and text()='About'])[1]")).click(); //the about link on top with white text
    }

    @Then("the users should be redirected to the about page {string}")
    public void the_users_should_be_redirected_to_the_about_page(String expected_Url) {
        String actual_Url = driver.getCurrentUrl();
        Assertions.assertEquals(expected_Url, actual_Url, "Expected URL: " + expected_Url + ", Actual URL: " + actual_Url);
    }

    @When("the user click on the about link in the bottom of the page")
    public void the_user_click_on_the_link_in_the_bottom_of_the_page() {
        driver.findElement(By.xpath("(//a[@class='nav-link px-2 text-muted' and text()='About'])[1]")).click();
    }

    @Given("user is in the About page")
    public void user_is_in_the_about_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/about");
    }

    @When("the user click on the To all products-button {string}")
    public void the_user_click_on_the_to_all_products_button(String buttonLabel) {
        WebElement button = driver.findElement(By.xpath("//button[contains(text(), '" + buttonLabel + "')]"));
        button.click();
    }

    @When("the user click on the Checkout button")
    public void theUserClickOnTheCheckoutButton() {
        driver.findElement(By.xpath("//a[contains(@class, 'btn-warning')]")).click();
    }

    @Then("the user should be redirected to the Checkout form page")
    public void theUserShouldBeRedirectedToThePage() {
        Assertions.assertTrue(driver.findElement(By.xpath("//h2[text()='Checkout form']")).isDisplayed(), "Element is not visible");
    }

    @When("the user clicks on the Home link")
    public void the_user_click_on_the_Home_link() {
        WebElement home = driver.findElement(By.partialLinkText("Home"));
        home.click();
    }

    @Then("the user should be directed to home page")
    public void the_user_should_be_directed_to_home_page() {
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/", actualUrl);
    }

    @When("user minimizes the screen")
    public void user_minimizes_the_screen() {
        driver.manage().window().minimize();
    }

    @Then("the application should be aligned automatically accordingly")
    public void the_application_should_be_aligned_automatically_accordingly() {
        WebElement aligned = driver.findElement(By.xpath("/html/body"));
        boolean display = aligned.isDisplayed();
        Assertions.assertTrue(display);
    }

    @When("user clicks on {string} button on the start page")
    public void userClicksOnButtonOnTheStartPage(String allProductsButton) {
        driver.findElement(By.xpath("//button[text()= '" + allProductsButton + "' ]")).click();
    }

    @Then("user should be redirected to {string} page")
    public void userShouldBeRedirectedToPage(String pageTitle) {
        Assertions.assertEquals(pageTitle, driver.getTitle(),"Page title does not match");
    }
}
