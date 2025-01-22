package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutProcess {
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

    @Test(dataProvider = "information-details")
    public void testVerifyInvalidCheckoutInformationFunctionality(String firstname, String lastName, String postalCode, String expectedMessage) {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(postalCode);
        driver.findElement(By.xpath("//input[@id='continue']")).click();
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        Assert.assertEquals(errorMessage,expectedMessage,"Error message is incorrect");
    }

       @DataProvider(name ="information-details")
   public Object[][] userCredentials(){
        return new Object[][]{
           {"","","","Error: First Name is required"} ,
           {"Thathsarani", "","","Error: Last Name is required"},
                {"Thathsarani","Sudusinghe","","Error: Postal Code is required"},
                {"Thathsarani","","82100","Error: Last Name is required"}

       };
   }
}
