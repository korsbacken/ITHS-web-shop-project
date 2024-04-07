package com.example.Team1webshop;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class ApiTests {

    private Response response; // Deklarerar response som en privat instansvariabelgit

    // Samuel
    @Test
    @DisplayName("Check endpoint status code and if all categories are available")
    public void webShopCategories() {
        baseURI = "https://produktapi-6ef53ba8f2f2.herokuapp.com";
        given().
            get("/products/categories").
        then().statusCode(200).body("", hasItems(
            "electronics",
            "jewelery",
            "men's clothing",
            "women's clothing"));
    }

    @Test //Mia
    public void Test_Womens_Endpoint_With_Valid_StatusCode() {
        response = given().baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com/products/categories/women's%20clothing").when().get();
        int actualStatusCode = response.getStatusCode(); // get the actual status code
        int expectedStatusCode = 200; // the expected statuscode
        Assertions.assertEquals(expectedStatusCode, actualStatusCode); // compare the expected value with the actual value
    }

    @Test //Mia
    public void Test_Womens_Endpoint_With_Correct_Products() {
        String[] expectedTitles = {
                "BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats",
                "Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket",
                "Rain Jacket Women Windbreaker Striped Climbing Raincoats",
                "MBJ Women's SolShort Sleeve Boat Neck V",
                "Opna Women's Short Sleeve Moisture",
                "DANVOUY Womens T Shirt Casual Cotton Short"
        };

        given()
                .baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com")
                .when()
                .get("/products/categories/women's clothing")
                .then()
                .body("title", hasItem(expectedTitles[0])) // Check if the first expected title is present
                .body("title", hasItem(expectedTitles[1]))
                .body("title", hasItem(expectedTitles[2]))
                .body("title", hasItem(expectedTitles[3]))
                .body("title", hasItem(expectedTitles[4]))
                .body("title", hasItem(expectedTitles[5]));
    }

    @Test //Mia
    public void Test_Womens_Endpoint_Has_correct_Price() {
        baseURI = "https://produktapi-6ef53ba8f2f2.herokuapp.com";
        given().
                get("/products/categories/women's clothing").
                then().statusCode(200).body("[0].price", equalTo(56.99F)); //test just the price of the first product
    }

    @Test //Mia
    public void Test_Mens_Endpoint_With_Valid_StatusCode() {
        response = given().baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com/products/categories/men's%20clothing").when().get();
        int actualStatusCode = response.getStatusCode(); // get the actual status code
        int expectedStatusCode = 200; // the expected statuscode
        Assertions.assertEquals(expectedStatusCode, actualStatusCode); // compare the expected value with the actual value
    }

    @Test // Mia
    public void Test_Mens_Endpoint_With_Invalid_StatusCode() {
        // give a wrong URL (added some more links to the URL)
        response = given().baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com/products/categories/mens%20/products/categories/").when().get(); //do a get with the wrong URL
        int actualStatusCode = response.getStatusCode();   // get the actual status code from that
        int expectedStatusCode = 404; // Declare the expected status code,  404, not found
        Assertions.assertEquals(expectedStatusCode, actualStatusCode); //compare the actual with expected
    }

    @Test //Mia
    public void Test_Mens_Endpoint_With_Correct_Products() {
        String[] expectedTitles = {
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                "Mens Casual Premium Slim Fit T-Shirts",
                "Mens Cotton Jacket",
                "Mens Casual Slim Fit"
        };

        given()
                .baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com")
                .when()
                .get("/products/categories/men's clothing")
                .then()
                .body("title", hasItem(expectedTitles[0])) // Check if the first expected title is present
                .body("title", hasItem(expectedTitles[1]))
                .body("title", hasItem(expectedTitles[2]))
                .body("title", hasItem(expectedTitles[3]));
    }

    @Test //Mia
    public void Test_Mens_Endpoint_Has_correct_Price() {
        baseURI = "https://produktapi-6ef53ba8f2f2.herokuapp.com";
        given().
                get("/products/categories/men's clothing").
                then().statusCode(200).body("[0].price", equalTo(109.95F)); //test just the price of the first product
    }

    // Samuel
    @Test
    @DisplayName("Check endpoint status code of electronics category and make sure it is not empty")
    public void webShopElectronicsCategory() {
        baseURI = "https://produktapi-6ef53ba8f2f2.herokuapp.com";
        given().
                get("/products/categories/electronics").
                then().statusCode(200).body("$", hasSize(greaterThan(0)));
    }

    // Samuel
    @Test
    @DisplayName("Verify the title of a product in the electronics category")
    public void webShopElectronicsCategoryProductDataTitle() {
        baseURI = "https://produktapi-6ef53ba8f2f2.herokuapp.com";
        given().
                get("/products/categories/electronics").
                then().statusCode(200).body("[0].title", equalTo("WD 2TB Elements Portable External Hard Drive - USB 3.0"));
    }

    //Semih
    @Test
    @DisplayName("Check endpoint status code of 'products' and number products")
    public void webShopProductsURL() {
        given()
                .baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com")
                .get("/products")
                .then().statusCode(200).body("[19].id", equalTo(20));

    }

    //Semih
    @Test
    @DisplayName("Check endpoint status code for specific product and products prodct details")
    public void webShopProductURL() {
        response = given().baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com/products/Fjallraven").when().get();
        int actualStatusCode= response.getStatusCode();
        Assertions.assertEquals(400, actualStatusCode); //Since we don't have specific endpoint for each product we do expect "400 not found" status code

        String actualProductDetails = response.body().asString();
        String expectedProductDetails= "\"id\":1,\"title\":\"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops\",\"price\":109.95,\"category\":\"men's clothing\",\"description\":\"Fin väska me plats för dator\",\"image\":\"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg\"";
        Assertions.assertNotEquals(expectedProductDetails,actualProductDetails); // that assertion should be updated as a assertEqual when each product has endpoint
    }

    // Samuel
    @Test
    @DisplayName("Verify the price of a product in the electronics category")
    public void webShopElectronicsCategoryProductDataPrice() {
        baseURI = "https://produktapi-6ef53ba8f2f2.herokuapp.com";
        given().
                get("/products/categories/electronics").
                then().statusCode(200).body("[1].price", equalTo(109F));
    }

    // Samuel
    @Test
    @DisplayName("Verify the id of a product in the electronics category")
    public void webShopElectronicsCategoryProductDataId() {
        baseURI = "https://produktapi-6ef53ba8f2f2.herokuapp.com";
        given().
                get("/products/categories/electronics").
                then().statusCode(200).body("[2].id", equalTo(11));
    }

    //divya
    @Test
    @DisplayName("Check endpoint status code of Jewelry category and make sure it is not empty")
    public void webShopJewelryCategoryValidURL() {
        response = given().baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com/products/categories/jewelery").when().get(); //do a get with the URL end point
        int actualStatusCode = response.getStatusCode();   // get the actual status code from that
        int expectedStatusCode = 200; // Declare the expected status code,  200, successful
        Assertions.assertEquals(expectedStatusCode, actualStatusCode); //compare the actual with expected
    }

    //divya
    @Test
    @DisplayName("Check endpoint status code of Jewelry category with wrong URL")
    public void webShopJewelryCategoryInvalidURL() {
        response = given().baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com/products/categories/jewelery/1").when().get(); //do a get with the wrong URL end point
        int actualStatusCode = response.getStatusCode();   // get the actual status code from that
        int expectedStatusCode = 404; // Declare the expected status code,  404, not found
        Assertions.assertEquals(expectedStatusCode, actualStatusCode); //compare the actual with expected
    }

    //divya
    @Test
    @DisplayName("Check response from the endpoint of Jewelry category")
    public void JewelryCategoryResponse() {
        response = given().baseUri("https://produktapi-6ef53ba8f2f2.herokuapp.com/products/categories/jewelery").when().get(); //do a get with the URL end point
        String actualJson = response.getBody().asString();// get the actual response Json data
        String expectedJson = "[{\"id\":5,\"title\":\"John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet\",\"price\":695.0,\"category\":\"jewelery\",\"description\":\"Silver drakens återkomst. Ett måste om man vill ha den!\",\"image\":\"https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg\"}," +
                                    "{\"id\":6,\"title\":\"SolGold Petite Micropave\",\"price\":168.0,\"category\":\"jewelery\",\"description\":\"Denna blir man glad av.\",\"image\":\"https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_.jpg\"}," +
                                    "{\"id\":7,\"title\":\"White Gold Plated Princess\",\"price\":9.99,\"category\":\"jewelery\",\"description\":\"Prinsessans bästa vän. Köp för att få den i din ägo\",\"image\":\"https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg\"}," +
                                    "{\"id\":8,\"title\":\"Pierced Owl Rose Gold Plated Stainless Steel Double\",\"price\":10.99,\"category\":\"jewelery\",\"description\":\"Något med en uggla, och i guld och lite dubbel stål\",\"image\":\"https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_.jpg\"}]";
        Assertions.assertEquals(expectedJson, actualJson); //compare the actual with expected
    }

    // Samuel
    @Test
    @DisplayName("Verify data of a product in the  category with specific id")
    public void webShopElectronicsProductData() {
        baseURI = "https://produktapi-6ef53ba8f2f2.herokuapp.com";
        String productId = "14"; // 1 - 20
        given()
            .pathParam("id", productId)
        .when()
            .get("/products/{id}")
        .then().statusCode(200)
            .body("title", equalTo("Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultraw Screen QLED"))
            .body("price", equalTo(999.99F))
            .body("category", equalTo("electronics"))
            .body("description", equalTo("En lite böjd skär Men den funkar ändå!"))
            .body("image", equalTo("https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg"));
    }

}
