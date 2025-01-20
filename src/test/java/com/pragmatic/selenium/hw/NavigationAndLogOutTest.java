package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NavigationAndLogOutTest {
    WebDriver driver;
    @BeforeMethod
    public void openTestLink(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage=driver.findElement(By.cssSelector("[data-test='title']")).getText();
        Assert.assertEquals(errorMessage,"Products","Product text not meet");
    }

    @Test
    public void testMenuFunctionality() {
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        WebElement menuItem  = driver.findElement(By.xpath("//div[@class='bm-menu-wrap']"));
        Assert.assertTrue(menuItem.isDisplayed(), "The menu is not visible");
    }

    @Test
    public void testVerifyLogoutFunctionality() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/"));
    }

}
