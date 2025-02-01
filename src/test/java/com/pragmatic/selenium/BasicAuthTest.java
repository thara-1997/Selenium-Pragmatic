package com.pragmatic.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasicAuthTest {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void testBasicAuth(){
        String username = "admin";
        String password = "admin";
        String baseUrl = "https://the-internet.herokuapp.com/basic_auth";
        String authenticatedUrl = "https://" +username+ ":" +password+ "@"
                +baseUrl.replace("https://", "");

        System.out.println("authenticatedUrl = " +authenticatedUrl);
        driver.get(authenticatedUrl);

        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Congratulations! You must have the proper credentials."));

    }
}
