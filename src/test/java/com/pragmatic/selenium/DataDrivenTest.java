package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataDrivenTest {
    WebDriver driver;
    @BeforeMethod
    public void openTestLink(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @Test
    public void testValidLoginCredentials(){

        //Type Username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //type password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        //click login
        driver.findElement(By.id("login-button")).click();

        String errorMessage=driver.findElement(By.cssSelector("[data-test='title']")).getText();
        //assert that product is in the title
        Assert.assertEquals(errorMessage,"Products","Product text not meet");


        //close the browser
       // driver.quit();
    }
    @Test
    public void loginWithBlankCredentials(){
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Username is required","Error Message is incorrect");
    }

    @Test
    public void loginWithBlankUsername(){
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Username is required","Error Message is incorrect");
    }
    @Test
    public void loginWithBlankPassword(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Password is required","Error Message is incorrect");
    }

    @Test
    public void loginWithInvalidCredentials(){
        driver.findElement(By.id("user-name")).sendKeys("ABCD");
        driver.findElement(By.id("password")).sendKeys("abcd");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Username and password do not match any user in this service","Error Message is incorrect");
    }

    @Test
    public void loginWithInvalidPassword(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("abcd");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    public void loginWithInvalidUsername(){
        driver.findElement(By.id("user-name")).sendKeys("ABCD");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,"Epic sadface: Username and password do not match any user in this service","Error Message is incorrect");
    }

    @Test(dataProvider = "login-credentials",dataProviderClass = DataProviderSauceLabs.class)
    public void testInvalidUserLogin(String username, String password, String expectedMessage) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage,expectedMessage,"Error Message is incorrect");
    }

//   @DataProvider(name ="login-credentials")
//   public Object[][] userCredentials(){
//        return new Object[][]{
//           {"","","Epic sadface: Username is required"} ,
//           {"", "secret_sauce","Epic sadface: Username is required"},
//                {"standard_user","","Epic sadface: Password is required"},
//                {"standard_user","invalid","Epic sadface: Username and password do not match any user in this service"}
//
//       };
//   }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
