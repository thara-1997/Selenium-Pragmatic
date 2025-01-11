package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HelloSeleniumTest {
    WebDriver driver;
    @BeforeMethod
    public void openTestLink(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @Test
    public void testHelloSelenium(){

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
    @Test
    public void loginWithBlankCredentials(){
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(),"Epic sadface: Username is required");
    }

    @Test
    public void loginWithBlankUsername(){
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(),"Epic sadface: Username is required");
    }
    @Test
    public void loginWithBlankPassword(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(),"Epic sadface: Password is required");
    }

    @Test
    public void loginWithInvalidCredentials(){
        driver.findElement(By.id("user-name")).sendKeys("ABCD");
        driver.findElement(By.id("password")).sendKeys("abcd");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void loginWithInvalidPassword(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("abcd");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
        //Cmmit
    }

    @Test
    public void loginWithInvalidUsername(){
        driver.findElement(By.id("user-name")).sendKeys("ABCD");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");
    }


}
