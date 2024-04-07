package com.example.Team1webshop.StepDefsTests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");                     // Uncomment this for Github Actions
        options.addArguments("--window-size=1920,1080");        // Uncomment this for Github Actions
//        options.addArguments("--incognito");                    // Uncomment this when testing locally
//        options.addArguments("--start-maximized");              // Uncomment this when testing locally
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
   public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

