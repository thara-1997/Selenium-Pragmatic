package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DisableSavePasswordPopupTest {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        //Create a map to disable password saving and autofill
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);// disable password manger
        prefs.put("profile.password_manager_enabled", false); //disable password manager popup

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testDisableBrowserPopup(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage=driver.findElement(By.cssSelector("[data-test='title']")).getText();
        Assert.assertEquals(errorMessage,"Products","Product text not meet");
    }

}
