package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KeyTest {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void afterMethod(){
        //driver.quit();
    }
    @Test
    public void testKeysOne(){
        driver.get("https://www.saucedemo.com/");
        WebElement username = driver.findElement(By.id("user-name"));

        Actions actions = new Actions(driver);

        actions.keyDown(Keys.SHIFT)
                .sendKeys(username,"p")
                .keyUp(Keys.SHIFT)
                .perform();

        actions.sendKeys(username, "ragmatic").perform();

        Assert.assertEquals(username.getDomProperty("value"),"Pragmatic");

    }
    @Test
    public void testKeysTwoCopyPaste(){
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement textInput = driver.findElement(By.id("my-text-id"));

        Actions actions = new Actions(driver);

        actions.sendKeys(textInput,"Selenium!")
                .sendKeys(textInput,Keys.ARROW_LEFT)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)
                .keyUp(Keys.SHIFT)
                .keyDown(Keys.CONTROL)
                .sendKeys("xvv")
                .keyUp(Keys.CONTROL)
                .perform();

        Assert.assertEquals(textInput.getDomProperty("value"),"SeleniumSelenium!");
    }

}
