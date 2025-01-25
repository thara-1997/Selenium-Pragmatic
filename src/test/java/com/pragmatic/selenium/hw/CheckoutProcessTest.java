package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CheckoutProcessTest {
    WebDriver driver;
    @BeforeMethod
    public void openTestLink(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginFunction();
    }
    public void loginFunction(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    public void addingItemsToCart(){
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }

    public void addCheckOutDetails(){
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Thathsarani");
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Nayanathara");
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }

    @Test(dataProvider = "information-details", dataProviderClass = DataProviderCheckoutProcess.class)
    public void testVerifyInvalidCheckoutInformationFunctionality(String firstname, String lastName, String postalCode, String expectedMessage) {
       addingItemsToCart();

        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(postalCode);
        driver.findElement(By.xpath("//input[@id='continue']")).click();

        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        Assert.assertEquals(errorMessage,expectedMessage,"Error message is incorrect");
    }



    @Test
    public void testVerifyValidCheckoutInformationFunctionality() {
        addingItemsToCart();
        addCheckOutDetails();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-two.html"));
        
    }

    @Test
    public void testVerifyOrderSummaryPage() {
        addingItemsToCart();
        addCheckOutDetails();
        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

        List<String> expectedCartItems = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");

        Assert.assertEquals(cartItems.size(),expectedCartItems.size(), "cart Items are not matching");

        for (int i =0; i<cartItems.size(); i++){
            String cartItemName = cartItems.get(i).getText();
            String expectedProductName = expectedCartItems.get(i);
            Assert.assertEquals(cartItemName,expectedProductName, "Cart Items are not matching");
        }

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='summary_total_label']")).getText(),"Total: $43.18", "Total Value is not matching");

    }

    @Test
    public void testVerifyFinishButtonFunctionality() {
        addingItemsToCart();
        addCheckOutDetails();
        driver.findElement(By.xpath("//button[@id='finish']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h2[normalize-space()='Thank you for your order!']")).getText(),"Thank you for your order!");
    }

    @Test
    public void testVerifyCancelButtonFunctionality() {
        addingItemsToCart();

        driver.findElement(By.xpath("//button[@id='cancel']")).click();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/cart.html"));
    }
}

