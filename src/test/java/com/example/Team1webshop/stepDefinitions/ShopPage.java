package com.example.Team1webshop.stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ShopPage {
    private final WebDriver driver;

    public ShopPage() {
        this.driver = Hooks.getDriver();
    }

    @When("user clicks on Add to cart button")
    public void user_clicks_on_add_to_cart_button() {
        driver.findElement(By.xpath("(//*[@class='btn btn-primary'])[1]")).click();

    }

    @Then("product should be added to the cart")
    public void product_should_be_added_to_the_cart() {
        driver.findElement(By.xpath("//*[@class='btn btn-warning']")).click();
        String actualProductTitle = driver.findElement(By.xpath("//*[@class='my-0 w-75']")).getText();
        String expectedProductTitle = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops";
        Assertions.assertEquals(actualProductTitle, expectedProductTitle);
    }

    @When("user clicks on checkout button")
    public void user_clicks_on_checkout_button() {
        driver.findElement(By.xpath("//*[@class='btn btn-warning']")).click();
    }

    @Then("user should be landed to checkout page")
    public void user_should_be_landed_to_checkout_page() {
        String expectedUrl = "https://webshop-agil-testautomatiserare.netlify.app/checkout";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }

    @Given("The user clicks on the shop page")
    public void setup() throws InterruptedException {
        WebElement shopButton = driver.findElement(By.xpath("/html//header//ul//a[@href='/products']"));
        shopButton.click();
        Thread.sleep(2000);
    }

    @Then("The user is navigated to the shop page")
    public void I_should_be_navigated_to_Shop_page() {
        String navigated_url = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/products", navigated_url);
    }

    @When("user clicks on the mens clothing from shop page")
    public void User_clicks_on_the_mens_clothing_from_shop_page() {
        WebElement filterMensClothing = driver.findElement(By.partialLinkText("Men's clothing"));
        filterMensClothing.click();
    }

    @Then("Only mens clothing products must be visible")
    public void only_mens_clothing_products_must_be_visible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mens_clothing = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
        List<WebElement> mens_product = mens_clothing.findElements(By.className("card-body"));
        int numberOfElements = mens_product.size();
        Assertions.assertEquals(4, numberOfElements);
    }

    @When("user clicks on the women's clothing from shop page")
    public void user_clicks_on_the_womens_clothing_from_shop_page() {
        WebElement filterWomensClothing = driver.findElement(By.partialLinkText("Women's clothing"));
        filterWomensClothing.click();
    }

    @Then("Only women's clothing products must be visible")
    public void only_womens_clothing_products_must_be_visible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womens_clothing = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
        List<WebElement> womens_product = womens_clothing.findElements(By.className("card-body"));
        int numberOfElements = womens_product.size();
        Assertions.assertEquals(6, numberOfElements);
    }

    @When("user clicks on the jewelery from shop page")
    public void user_clicks_on_the_jewelery_from_shop_page() {
        WebElement filterjewelery = driver.findElement(By.partialLinkText("Jewelery"));
        filterjewelery.click();
    }

    @Then("Only jewelery products must be visible")
    public void only_jewelery_products_must_be_visible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement jewelery = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
        List<WebElement> jewelery_product = jewelery.findElements(By.className("card-body"));
        int numberOfElements = jewelery_product.size();
        Assertions.assertEquals(4, numberOfElements);
    }

    @When("User clicks on the electronics from shop page")
    public void user_clicks_on_the_electronics_from_shop_page() {
        WebElement filterelectronics = driver.findElement(By.partialLinkText("Electronics"));
        filterelectronics.click();
    }

    @Then("Only electronic products must be visible")
    public void only_electronic_products_must_be_visible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement electronics = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
        List<WebElement> electronics_product = electronics.findElements(By.className("card-body"));
        int numberOfElements = electronics_product.size();
        Assertions.assertEquals(6, numberOfElements);
    }

    @When("User clicks on the all from shop page")//divya
    public void user_clicks_on_the_all_from_shop_page() {
        WebElement all = driver.findElement(By.partialLinkText("All"));
        all.click();
    }

    @Then("Only all products must be visible")
    public void only_all_products_must_be_visible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement all = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
        List<WebElement> all_products = all.findElements(By.className("card-body"));
        int numberOfElements = all_products.size();
        Assertions.assertEquals(20, numberOfElements);
    }

    @When("User searches with specific word in the search field {string}")
    public void i_enter_a_in_the_search_field(String keyword) {
        WebElement searchField = driver.findElement(By.id("search"));
        searchField.sendKeys(keyword);
    }

    @Then("Relevant results are displayed")
    public void relevant_results_are_displayed() {
        List<WebElement> searchResults = driver.findElements(By.className("card-body"));
        Assertions.assertTrue((int) searchResults.size() > 0);
    }

    @And("Results contain the keyword {string}")
    public void results_contain_the_keyword(String keyword) {
        List<WebElement> searchResults = driver.findElements(By.className("card-body"));
        boolean keywordFound = false;
        for (WebElement result : searchResults) {
            if (result.getText().toLowerCase().contains(keyword.toLowerCase())) {
                keywordFound = true;
                break;
            }
        }
        Assertions.assertTrue(keywordFound);
    }

    @When("adding {int} products to the cart")
    public void addingAProductsToTheCart(Integer productsAdded) {
        for (int i = 0; i < productsAdded; i++) {
            driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]")).click();
        }
    }

    @Then("number of products in the cart should be {int}")
    public void number_of_products_in_the_cart_should_be(Integer productsInCart) {
        String amount = driver.findElement(By.id("buttonSize")).getText();
        Assertions.assertEquals(productsInCart.toString(), amount);
    }
}



