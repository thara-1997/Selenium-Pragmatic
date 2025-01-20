package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class MethodTest {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void testTitle(){
        String baseUrl = "https://opensource-demo.orangehrmlive.com/";
        driver.get(baseUrl);
        Assert.assertEquals(driver.getTitle(),"OrangeHRM", "Title is incorrect");
    }

    @Test
    public void testPageSource(){
        String baseUrl = "https://opensource-demo.orangehrmlive.com/";
        driver.get(baseUrl);
        Assert.assertEquals(driver.getPageSource(),"OrangeHRM", "Page title is not correct");
    }

    @Test
    public void testNavigation() {
        String baseUrl = "https://opensource-demo.orangehrmlive.com/";
       // driver.get(baseUrl);
        driver.navigate().to(baseUrl);
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

    }

    @Test
    public void testFindElements() {
        String baseUrl = "https://www.saucedemo.com/";
        driver.get(baseUrl);

        List<WebElement> elements = driver.findElements(By.tagName("input"));

    }

    @Test
    public void getCurrentURL(){
        String baseUrl = "https://opensource-demo.orangehrmlive.com/";
        driver.get(baseUrl);
        Assert.assertTrue(driver.getCurrentUrl().startsWith(baseUrl));
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
