package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CartTest {
    WebDriver driver;
    @BeforeMethod
    public void openTestLink(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginFunction();


    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
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
    }

    @Test
    public void testVerifyCorrectProductDisplaying() {

        //the method that use to locate elements when using complex xpath
        //driver.findElement(new ByText(("Sauce Labs Backpack"))).click();
        // when using partial text
        //driver.findElement(new ByPartialText("Sause")).click();
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        String itemDescription = driver.findElement(By.xpath("//div[@class='inventory_item_desc']")).getText();
        Assert.assertEquals(itemDescription,
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "Item description is not matching");

        String itemPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
        Assert.assertEquals(itemPrice, "$29.99", "Item Price is not matching");
    }

    @Test
    public void testVerifyMultipleProductsInCart() {
        addingItemsToCart();

        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

        List<String> expectedCartItems = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");

        Assert.assertEquals(cartItems.size(),expectedCartItems.size(), "cart Items are not matching");

        for (int i =0; i<cartItems.size(); i++){
            String cartItemName = cartItems.get(i).getText();
            String expectedProductName = expectedCartItems.get(i);
            Assert.assertEquals(cartItemName,expectedProductName, "Cart Items are not matching");
        }
    }

    @Test
    public void testVerifyRemoveCartItemFunctionality(){
        addingItemsToCart();
        driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).click();

        List<WebElement> cartListItems = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        Assert.assertEquals(cartListItems.size(), 1, "Cart list Items count is not matching");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText(),"1", "Cart Item count is not matching");

    }

    @Test
    public void testVerifyContinueShoppingButtonFunctionality() {
        addingItemsToCart();
        driver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void testVerifyCheckoutFunctionality() {
        addingItemsToCart();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-one.html"));
    }
}
