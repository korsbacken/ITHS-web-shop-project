package com.example.Team1webshop.StepDefsTests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CheckoutPageStepDefs {

    private final WebDriver driver;

    Actions actions;

    public CheckoutPageStepDefs() {
        this.driver = Hooks.getDriver();
    }

    //Mia
    @Given("User has navigated to the checkout page")
    public void user_has_navigated_to_the_checkout_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/checkout");
    }

    //Mia
    @Given("User has navigated to the product page")
    public void user_has_navigated_to_the_product_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products");
    }

    //Mia
    @When("User clicks on the {string} button")
    public void user_clicks_on_the_button(String buttonLabel) {
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), '" + buttonLabel + "')]"));
        addButton.click();
    }

    //Mia
    @When("User navigates to the checkout page")
    public void user_navigates_to_the_checkout_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/checkout");
    }

    //Mia
    @When("User removes 1 item")
    public void user_removes_1_item() {
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(), 'Remove')]"));
        removeButton.click();
    }

    //Mia
    @Then("The item count in the shopping cart should decrease by 1")
    public void the_item_count_in_the_shopping_cart_should_decrease_by_1() {
        WebElement itemCountElement = driver.findElement(By.id("cartSize"));
        int updatedItemCount = Integer.parseInt(itemCountElement.getText());
        Assertions.assertEquals(0, updatedItemCount);
    }

    //Mia
    @Given("Users has navigated to the product page")
    public void users_has_navigated_to_the_product_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products");
    }

    //Mia
    @When("User clicks on the {string} button {int} times")
    public void user_clicks_on_the_button_multiple_times(String buttonLabel, int times) {
        for (int i = 0; i < times; i++) {
            WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), '" + buttonLabel + "')]"));
            addButton.click();
        }
    }

    //Mia
    @When("Users navigates to the checkout page")
    public void users_navigates_to_the_checkout_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/checkout");
    }

    //Mia
    @Then("The item count in the shopping cart should be {int}")
    public void the_item_count_in_the_shopping_cart_should_be(int expectedItemCount) {
        WebElement itemCountElement = driver.findElement(By.id("cartSize"));
        int updatedItemCount = Integer.parseInt(itemCountElement.getText());
        Assertions.assertEquals(expectedItemCount, updatedItemCount);
    }

    // Samuel
    @When("User adds a specific product to the cart {string}")
    public void user_adds_a_specific_product_to_the_cart(String addedProduct) {
        actions = new Actions(driver);
        List<WebElement> productCards = driver.findElements(By.className("card-body"));
        for (WebElement productCard : productCards) {
            WebElement productTitleElement = productCard.findElement(By.className("card-title"));
            String productTitle = productTitleElement.getText();
            if (productTitle.equals(addedProduct)) {
                actions.moveToElement(productCard);
                actions.perform();
                productCard.findElement(By.className("btn-primary")).click();
            }
        }
    }

    // Samuel
    @Then("User should see same product on checkout page {string}")
    public void user_should_see_same_product_on_checkout_page(String addedProduct) {
        WebElement productElement = driver.findElement(By.xpath("//ul[@id='cartList']//h6"));
        String productText = productElement.getText();
        Assertions.assertEquals(addedProduct, productText, "Product does not match");
    }

    // Samuel
    @When("User adds some sample products to cart")
    public void userAddsSomeSampleProductsToCart() {
        actions = new Actions(driver);
        String[] productTitles = {
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                "Pierced Owl Rose Gold Plated Stainless Steel Double",
                "Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5",
                "Rain Jacket Women Windbreaker Striped Climbing Raincoats"
        };
        for (String product : productTitles) {
            WebElement element = driver.findElement(By.xpath("//h3[text()='" + product + "']/following-sibling::button"));
            actions.moveToElement(element);
            actions.perform();
            element.click();
        }
    }

    //Semih
    @When("User added multiple products to the cart")
    public void user_added_multiple_products_to_the_cart() {
        // Commenting out this since we already have a wait function
        //wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.findElement(By.xpath("(//*[@class='btn btn-primary'])[1]")).click();
        driver.findElement(By.xpath("(//*[@class='btn btn-primary'])[2]")).click();
        driver.findElement(By.xpath("(//*[@class='btn btn-primary'])[3]")).click();
    }

    //Semih
    @Then("Products should be added to cart")
    public void products_should_be_added_to_cart() {
        //wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.findElement(By.xpath("//*[@class='btn btn-warning']")).click();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        String actualFirstProductname = driver.findElement(By.xpath("(//*[@class='my-0 w-75'])[1]")).getText();
        String actualSecondProductname = driver.findElement(By.xpath("(//*[@class='my-0 w-75'])[2]")).getText();
        String actualThirdProductname = driver.findElement(By.xpath("(//*[@class='my-0 w-75'])[3]")).getText();

        Assertions.assertAll("product names in the cart",
                () -> Assertions.assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops", actualFirstProductname),
                () -> Assertions.assertEquals("Mens Casual Premium Slim Fit T-Shirts", actualSecondProductname),
                () -> Assertions.assertEquals("Mens Cotton Jacket", actualThirdProductname)
        );
    }

    //Semih
    @When("User clicks on the checkout button")
    public void user_clicks_on_the_checkout_button() {
        driver.findElement(By.xpath("//*[@class='btn btn-warning']")).click();
    }

    //Semih
    @Then("User should be navigated to checkout page")
    public void user_should_be_navigated_to_checkout_page() {
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://webshop-agil-testautomatiserare.netlify.app/checkout";
        Assertions.assertEquals(expectedURL, actualURL);
    }

    //Semih
    @When("User clicks on shop button")
    public void user_clicks_on_shop_button() {
        driver.findElement(By.xpath("(//*[@class='nav-link px-2 text-white'])[2]")).click();
    }

    //Semih
    @Then("User should be navigated to shopping page")
    public void user_should_be_navigated_to_shopping_page() {
        //wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://webshop-agil-testautomatiserare.netlify.app/products";
        Assertions.assertEquals(expectedURL, actualURL);
    }

    //Semih
    @When("User add to cart button three times for the same product")
    public void user_add_to_cart_button_three_times_for_the_same_product() {
        driver.findElement(By.xpath("(//*[@class='btn btn-primary'])[3]")).click();
        driver.findElement(By.xpath("(//*[@class='btn btn-primary'])[3]")).click();
        driver.findElement(By.xpath("(//*[@class='btn btn-primary'])[3]")).click();
    }

    //Semih
    @Then("three pieces of same products should be added e cart")
    public void three_pieces_of_same_products_should_be_added_e_cart() {
        // wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.findElement(By.xpath("//*[@class='btn btn-warning']")).click();
        String actualFirtsProductname = driver.findElement(By.xpath("(//*[@class='my-0 w-75'])[1]")).getText();
        String actualSecondProductname = driver.findElement(By.xpath("(//*[@class='my-0 w-75'])[2]")).getText();
        String actualThirdProductname = driver.findElement(By.xpath("(//*[@class='my-0 w-75'])[3]")).getText();

        Assertions.assertEquals("3", driver.findElement(By.xpath(" //*[@class='badge bg-primary rounded-pill']")).getText());

        Assertions.assertAll("product names in the cart",
                () -> Assertions.assertEquals("Mens Cotton Jacket", actualFirtsProductname),
                () -> Assertions.assertEquals("Mens Cotton Jacket", actualSecondProductname),
                () -> Assertions.assertEquals("Mens Cotton Jacket", actualThirdProductname)
        );
    }

    //Semih
    @Then("Credit card option should be selected as a default payment method")
    public void credit_card_option_should_be_selected_as_a_default_payment_method() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        //wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean paymentMethodOption = driver.findElement(By.xpath("(//input[@type='radio'])[1]")).isSelected();
        Assertions.assertTrue(paymentMethodOption);
    }

    //Semih
    @Then("User should be able select debit cart or paypal as well as a payment methods")
    public void user_should_be_able_select_debit_cart_or_paypal_as_well_as_a_payment_methods() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//input[@type='radio'])[3]")).click();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean paypalCardOption = driver.findElement(By.xpath("(//input[@type='radio'])[3]")).isSelected();
        Assertions.assertTrue(paypalCardOption);
        driver.findElement(By.xpath("(//input[@type='radio'])[2]")).click();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean debitCardOption = driver.findElement(By.xpath("(//input[@type='radio'])[2]")).isSelected();
        Assertions.assertTrue(debitCardOption);
    }

    // Samuel
    @Then("User should see correct total price")
    public void userShouldSeeCorrectTotalPrice() {
        WebElement cartList = driver.findElement(By.id("cartList"));
        List<WebElement> listItems = cartList.findElements(By.tagName("li"));
        WebElement lastListItem = listItems.get(listItems.size() - 1);
        WebElement lastElementInLastListItem = lastListItem.findElement(By.tagName("*"));
        WebElement nextElement = lastElementInLastListItem.findElement(By.xpath("following-sibling::*"));
        String nextElementText = nextElement.getText();
        String totalPriceTemp = nextElementText.substring(1);
        float totalPrice = Float.parseFloat(totalPriceTemp);
        Assertions.assertEquals(269.93F, totalPrice, "Total price is not correct");
    }
}
