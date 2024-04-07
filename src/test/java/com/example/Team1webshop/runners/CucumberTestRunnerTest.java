package com.example.Team1webshop.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/example/Team1webshop/resources/features", // Directory containing feature files
        glue = {"com.example.Team1webshop.stepDefinitions"}, // Specifies step definition package
        plugin = {"pretty", "html:target/cucumber-reports"} // Plugins for generating reports
)
public class CucumberTestRunnerTest {
}