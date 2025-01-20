package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {
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
    public void testVerifyCorrectProductDisplaying() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        String itemDescription = driver.findElement(By.xpath("//div[@class='inventory_item_desc']")).getText();
        Assert.assertEquals(itemDescription,
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "Item description is not matching");

        String itemPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
        Assert.assertEquals(itemPrice, "$29.99", "Item Price is not matching");
    }
}
