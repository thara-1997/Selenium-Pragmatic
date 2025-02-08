package com.pragmatic.selenium.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
WebDriver driver;
    @BeforeMethod
    public void openTestLink(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(groups = {"smoke"})
    public void testValidLoginCredentials(){

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage=driver.findElement(By.cssSelector("[data-test='title']")).getText();
        Assert.assertEquals(errorMessage,"Products","Product text not meet");
    }

    @Test(groups = {"regression"})
    public void loginWithInvalidPassword() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("abcd");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void loginWithInvalidUsername(){
        driver.findElement(By.id("user-name")).sendKeys("ABCD");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Username and password do not match any user in this service","Error Message is incorrect");
    }

    @Test
    public void loginWithBlankUsername(){
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Username is required","Error Message is incorrect");
    }

    @Test
    public void testLoginWithLockedOutUserCredentials() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Sorry, this user has been locked out.","Error Message is incorrect" );
    }

    @Test
    public void testLoginWithPerformanceGlitchUser() {
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessage=driver.findElement(By.cssSelector("[data-test='title']")).getText();
        Assert.assertEquals(errorMessage,"Products","Product text not meet");
    }

    @Test
    public void testVerifyPlaceholderOfUserAndPassword() {
        String placeholderUsername=driver.findElement(By.id("user-name")).getAttribute("placeholder");
        Assert.assertEquals(placeholderUsername,"Username", "Placeholder is not matching");

        String placeholderPassword = driver.findElement(By.id("password")).getAttribute("placeholder");
        Assert.assertEquals(placeholderPassword, "Password", "Placeholder value is not matching");
    }

}
