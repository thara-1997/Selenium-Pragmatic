package com.pragmatic.selenium.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/resources/Features/login.feature"},
        glue = {"com.pragmatic.selenium.steps"},
        //name = {"Login with invalid credentials"},
        //tags = "@smoke or @regression",
        plugin = {"pretty", //Prints Gherkin steps in the console
                "html:target/cucumber-reports.html", // HTML report
                "json:target/cucumber.json"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {


}
