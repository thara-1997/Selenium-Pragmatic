package com.pragmatic.selenium.PageFactoryTests;

import com.pragmatic.selenium.DataProviderSauceLabs;
import com.pragmatic.selenium.PageFactoryPages.ProductListWithPageFactoryPage;
import com.pragmatic.selenium.PageFactoryPages.SauceLoginWithPageFactoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void testLoginWithValidCredentials(){
        SauceLoginWithPageFactoryPage sauceLoginWithPageFactoryPage = new SauceLoginWithPageFactoryPage(driver);
        ProductListWithPageFactoryPage productListWithPageFactoryPage = new ProductListWithPageFactoryPage(driver);
        sauceLoginWithPageFactoryPage.typeUsername("standard_user").typePassword("secret_sauce").clickLogin();
        Assert.assertEquals(productListWithPageFactoryPage.getTitle(),"Products", "Title is not matching");
    }
    @Test(dataProvider = "login-credentials",dataProviderClass = DataProviderSauceLabs.class,description = "verifyInvalidLogin")
    public void testLoginWithInvalidCredentials(String username, String password, String expectedError){
        SauceLoginWithPageFactoryPage sauceLoginWithPageFactoryPage = new SauceLoginWithPageFactoryPage(driver);
        sauceLoginWithPageFactoryPage.typeUsername(username).typePassword(password).clickLogin();
        Assert.assertEquals(sauceLoginWithPageFactoryPage.getError(),expectedError,"Error message is not correct");
    }
}
