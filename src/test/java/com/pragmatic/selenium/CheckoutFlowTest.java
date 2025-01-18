package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutFlowTest {
    WebDriver driver;
    @BeforeClass
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(priority = 1)
    public void testLogin(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage=driver.findElement(By.cssSelector("[data-test='title']")).getText();

        Assert.assertEquals(errorMessage,"Products","Product text not meet");
    }

    @Test(priority = 2)
    public void testCheckout(){
        WebElement backpackLink = driver.findElement(By.id("item_4_title_link"));
        backpackLink.click();
        String pageText = driver.findElement(By.xpath("//div[text() = 'Sauce Labs Backpack']")).getText();
        System.out.println(pageText);
        Assert.assertEquals(pageText,"Sauce Labs Backpack", "Page text is not matching");

        WebElement cartBtn = driver.findElement(By.id("add-to-cart"));
        cartBtn.click();

        WebElement cartIcon = driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']"));
        cartIcon.click();

        String cartTitle = driver.findElement(By.xpath("//span[@data-test='title']")).getText();


        Assert.assertEquals(cartTitle,"Your Cart", "Page text is not matching");

    }
    @Test(priority = 3)
    public void testVerifyCheckout(){
        WebElement checkoutBtn = driver.findElement(By.cssSelector("#checkout"));
        checkoutBtn.click();

        String title = driver.findElement(By.xpath("//span[@data-test='title']")).getText();
        Assert.assertEquals(title, "Checkout: Your Information", "Title is not matching");

        WebElement firstNameField = driver.findElement(By.id("first-name"));
        firstNameField.sendKeys("Thathsarani");
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        lastNameField.sendKeys("Nayanathara");
        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        postalCodeField.sendKeys("82100");

        WebElement buttonContinue = driver.findElement(By.id("continue"));
        buttonContinue.click();

        String checkoutOverview= driver.findElement(By.xpath("//span[text()= 'Checkout: Overview']")).getText();
        Assert.assertEquals(checkoutOverview,"Checkout: Overview");

        WebElement btnFinish = driver.findElement(By.id("finish"));
        btnFinish.click();

        String headerText = driver.findElement(By.xpath("//h2[@data-test = 'complete-header']")).getText();
        Assert.assertEquals(headerText, "Thank you for your order!", "Header is not matching");

    }

}
