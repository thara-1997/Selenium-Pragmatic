package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.DataProviderSauceLabs;
import com.pragmatic.selenium.pages.SauceCheckoutPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import com.pragmatic.selenium.pages.SauceProductListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    public void testVerifyLoginWithValidCredentials(){
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        loginPage.typeUserName("standard_user").typePassword("secret_sauce").clickLogin();
        Assert.assertEquals(sauceProductListPage.getTitle(),"Products","Product text not meet");
    }
    @Test(dataProvider = "login-credentials",dataProviderClass = DataProviderSauceLabs.class,description = "verifyInvalidLogin")
    public void testVerifyLoginWithInvalidCredentials(String username, String password, String expectedError){
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.typeUserName(username).typePassword(password).clickLogin();
        Assert.assertEquals(loginPage.getError(), expectedError,"Error message is not matching");
    }
    @Test
    public void testVerifyLoginWithLockedOutUserCredentials() {
        SauceLoginPage sauceLoginPage = new SauceLoginPage(driver);
        sauceLoginPage.typeUserName("locked_out_user").typePassword("secret_sauce").clickLogin();
        Assert.assertEquals(sauceLoginPage.getError(),"Epic sadface: Sorry, this user has been locked out.","Error Message is incorrect" );
    }

    @Test
    public void testVerifyLoginWithPerformanceGlitchUser() {
        SauceLoginPage sauceLoginPage = new SauceLoginPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceLoginPage.typeUserName("performance_glitch_user").typePassword("secret_sauce").clickLogin();
        Assert.assertEquals(sauceProductListPage.getTitle(),"Products","Product text not meet");
    }

    @Test
    public void testVerifyPlaceholderOfUserAndPassword() {

        SauceLoginPage sauceLoginPage = new SauceLoginPage(driver);

        SoftAssert softAssert =new SoftAssert();

        softAssert.assertEquals(sauceLoginPage.getUsernamePlaceholder(),"Username", "Placeholder is not matching");
        softAssert.assertEquals(sauceLoginPage.getPasswordPlaceHolder(), "Password", "Placeholder value is not matching");
        softAssert.assertAll();
    }


}
