package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        testLoginWithValidCredentials();
    }
    public void testLoginWithValidCredentials(){
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.typeUserName("standard_user").typePassword("secret_sauce").clickLogin();
    }

    @AfterMethod
    public void afterMethod(){
        //driver.quit();
    }
}
