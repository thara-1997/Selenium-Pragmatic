package com.pragmatic.selenium.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps {
    private WebDriver driver;

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }
        driver.quit();
    }

    @Given("user has accessed the login page")
    public void userHasAccessedTheLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("user provide valid credentials")
    public void userProvideValidCredentials() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Then("the user should be directed to the product inventory page")
    public void theUserShouldBeDirectedToTheProductInventoryPage() {
        String actualTitle = driver.findElement(By.cssSelector("[data-test='title']")).getText();
        Assert.assertEquals(actualTitle, "Products", "Products text not meet");
    }

    @When("user has entered invalid credentials {string}, {string}")
    public void userHasEnteredInvalidCredentials(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Then("the user should be see the error message {string}")
    public void theUserShouldBeSeeTheErrorMessage(String expectedError) {
        String actualTitle = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(actualTitle, expectedError, "error message is not correct");
    }

}
