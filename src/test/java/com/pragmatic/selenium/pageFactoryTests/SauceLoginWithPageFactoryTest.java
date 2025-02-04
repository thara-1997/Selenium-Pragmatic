package com.pragmatic.selenium.pageFactoryTests;

import com.pragmatic.selenium.DataProviderSauceLabs;
import com.pragmatic.selenium.pageFactoryPages.ProductListWithPageFactoryPage;
import com.pragmatic.selenium.pageFactoryPages.SauceLoginWithPageFactoryPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import com.pragmatic.selenium.pages.SauceProductListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SauceLoginWithPageFactoryTest {
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
        SauceLoginWithPageFactoryPage sauceLoginWithPageFactoryPage = new SauceLoginWithPageFactoryPage(driver);
        sauceLoginWithPageFactoryPage.typeUsername("standard_user").typePassword("secret_sauce").clickLogin();

        ProductListWithPageFactoryPage productListWithPageFactoryPage = new ProductListWithPageFactoryPage(driver);
        Assert.assertEquals(productListWithPageFactoryPage.getTitle(),"Products", "Title is not matching");
    }
    @Test(dataProvider = "login-credentials",dataProviderClass = DataProviderSauceLabs.class,description = "verifyInvalidLogin")
    public void testVerifyLoginWithInvalidCredentials(String username, String password, String expectedError){
        SauceLoginWithPageFactoryPage sauceLoginWithPageFactoryPage = new SauceLoginWithPageFactoryPage(driver);
        sauceLoginWithPageFactoryPage.typeUsername(username).typePassword(password).clickLogin();
        Assert.assertEquals(sauceLoginWithPageFactoryPage.getError(),expectedError,"Error message is not correct");
    }

    @Test
    public void testVerifyLoginWithLockedOutUserCredentials() {
        SauceLoginWithPageFactoryPage sauceLoginWithPageFactoryPage = new SauceLoginWithPageFactoryPage(driver);
        sauceLoginWithPageFactoryPage.typeUsername("locked_out_user").typePassword("secret_sauce").clickLogin();
        Assert.assertEquals(sauceLoginWithPageFactoryPage.getError(),"Epic sadface: Sorry, this user has been locked out.","Error Message is incorrect" );
    }

    @Test
    public void testVerifyLoginWithPerformanceGlitchUser() {
        SauceLoginWithPageFactoryPage sauceLoginWithPageFactoryPage = new SauceLoginWithPageFactoryPage(driver);
        sauceLoginWithPageFactoryPage.typeUsername("performance_glitch_user").typePassword("secret_sauce").clickLogin();

        ProductListWithPageFactoryPage productListWithPageFactoryPage = new ProductListWithPageFactoryPage(driver);
        Assert.assertEquals(productListWithPageFactoryPage.getTitle(),"Products","Product text not meet");
    }

    @Test
    public void testVerifyPlaceholderOfUserAndPassword() {

        SauceLoginWithPageFactoryPage sauceLoginWithPageFactoryPage = new SauceLoginWithPageFactoryPage(driver);

        SoftAssert softAssert =new SoftAssert();

        softAssert.assertEquals(sauceLoginWithPageFactoryPage.getUsernamePlaceholder(),"Username", "Placeholder is not matching");
        softAssert.assertEquals(sauceLoginWithPageFactoryPage.getPasswordPlaceholder(), "Password", "Placeholder value is not matching");
        softAssert.assertAll();
    }

}
