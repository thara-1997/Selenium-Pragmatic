package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.DataProviderSauceLabs;
import com.pragmatic.selenium.pages.SauceLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLoginTest {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
    @Test
    public void testLoginWithValidCredentials(){
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.typeUserName("standard_user").typePassword("secret_sauce").clickLogin();
    }
    @Test(dataProvider = "login-credentials",dataProviderClass = DataProviderSauceLabs.class,description = "verifyInvalidLogin")
    public void testLoginWithInvalidCredentials(String username, String password, String expectedError){
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.typeUserName(username).typePassword(password).clickLogin();
        Assert.assertEquals(loginPage.getError(), expectedError,"Error message is not matching");
    }

}
