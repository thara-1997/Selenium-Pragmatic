package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductListPageTest {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
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

    @Test
    public void testVerifyAllProductsAreDisplayingInCorrectNameAndPrice() {
        List<WebElement> productListItems =driver.findElements(By.xpath("//div[@class='inventory_item']"));

        String[][] expectedProducts = {
                {"Sauce Labs Backpack", "$29.99", "sauce-backpack-1200x1500.0a0b85a3.jpg"},
                {"Sauce Labs Bike Light", "$9.99", "bike-light-1200x1500.37c843b0.jpg"},
                {"Sauce Labs Bolt T-Shirt", "$15.99", "bolt-shirt-1200x1500.c2599ac5.jpg"},
                {"Sauce Labs Fleece Jacket", "$49.99", "sauce-pullover-1200x1500.51d7ffaf.jpg"},
                {"Sauce Labs Onesie", "$7.99", "red-onesie-1200x1500.2ec615b2.jpg"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99", "red-tatt-1200x1500.30dadef4.jpg"}
        };

        for (int i = 0; i< productListItems.size(); i++){
            WebElement product = productListItems.get(i);
            // Get actual product details
            String actualName = product.findElement(By.className("inventory_item_name")).getText();
            String actualPrice = product.findElement(By.className("inventory_item_price")).getText();
//
            String expectedName = expectedProducts[i][0];
            String expectedPrice = expectedProducts[i][1];
//
            Assert.assertEquals(actualName,expectedName,"Product name mismatch for " + expectedName);
            Assert.assertEquals(actualPrice,expectedPrice,"Product price mismatch for " + expectedName);
        }


    }

    @Test
    public void testVerifyProductPage() {
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).getText(),
                "Sauce Labs Backpack", "Product name is not matching");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText(),"$29.99");
    }

    @Test
    public void testClickAddToCartButtonForEachProduct() {
         List<WebElement> cartButtons = driver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']"));
         for (WebElement addToCartButton: cartButtons){
             addToCartButton.click();
         }
         Assert.assertEquals(driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText(),"6", "Cart Item count is not matching");
    }

    @Test
    public void testRemoveButtonFunctionality() {
        List<WebElement> cartButtons = driver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']"));
        for (WebElement addToCartButton: cartButtons){
            addToCartButton.click();
        }
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory ']"));
        for (WebElement removeBtn: removeButtons){
            removeBtn.click();
        }

    }
}
