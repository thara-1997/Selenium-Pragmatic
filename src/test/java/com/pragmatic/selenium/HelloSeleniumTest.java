package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloSeleniumTest {
    @Test
    public void testHelloSelenium(){
        //create browser instance
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //navigate to login page
        driver.get("https://www.saucedemo.com/");

        //Type Username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //type password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        //click login
        driver.findElement(By.id("login-button")).click();

        //assert that product is in the title
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");


        //close the browser
       // driver.quit();
    }


}
