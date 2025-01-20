package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class EasyToSyncButtonTest {

    private final String baseUrl = "https://eviltester.github.io/synchole/buttons.html";
    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }
    @AfterMethod
    public void afterMethod(){
      driver.quit();
    }

    @Test
    public void testEasyToSyncButtons() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        clickButton(By.id("easy00"));
        clickButton(By.id("easy01"));
        clickButton(By.id("easy02"));
        clickButton(By.id("easy03"));
        Assert.assertEquals(driver.findElement(By.id("easybuttonmessage")).getText(), "All Buttons Clicked");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    private void clickButton(By by){
        driver.findElement(by).click();
    }

    @Test
    public void testHardToSyncButtons() {



        waitAndClick(By.id("button00"));
        waitAndClick(By.id("button01"));
        waitAndClick(By.id("button02"));
        waitAndClick(By.id("button03"));


        Assert.assertEquals(driver.findElement(By.id("buttonmessage")).getText(), "All Buttons Clicked");

    }

    private void waitAndClick(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.withMessage("Button is not available to click");
        //wait.pollingEvery(Duration.ofMillis(100));
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(by),
                ExpectedConditions.elementToBeClickable(by)
                ));
        clickButton(by);
    }


}
