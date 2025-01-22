package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SyncHoleTest {
     private final String baseUrl = "https://eviltester.github.io/synchole/collapseable.html";
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
     public void testClickingAboutLinkWithWaitForElementTobeClickableWithWebElement(){
        driver.findElement(By.cssSelector("div#collapsable")).click();
        WebElement aboutLink = driver.findElement(By.id("aboutlink"));

        //wait till element to be clickable or visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(50));
        wait.until(ExpectedConditions.elementToBeClickable(aboutLink));
        aboutLink.click();
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(),"About The Sync Hole");
    }


     @Test
    public void testClickingAboutLinkWithWaitForElementTobeVisible(){
         driver.findElement(By.cssSelector("div#collapsable")).click();
         WebElement aboutLink = driver.findElement(By.id("aboutlink"));

         //wait till element to be clickable or visible
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(50));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#collapsable")));
         aboutLink.click();
         Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(),"About The Sync Hole");
     }
    @Test
    public void testClickingAboutLinkWithWaitForElementTobeClickable(){
        driver.findElement(By.cssSelector("div#collapsable")).click();
        WebElement aboutLink = driver.findElement(By.id("aboutlink"));

        //wait till element to be clickable or visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(50));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#collapsable")));
        aboutLink.click();
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(),"About The Sync Hole");
    }




}
