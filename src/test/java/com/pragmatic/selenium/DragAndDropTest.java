package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropTest {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public void testDragAndDrop(){
        WebElement sauceElement = driver.findElement(By.id("column-a"));
        WebElement targetElement = driver.findElement(By.id("column-b"));

        Assert.assertEquals(sauceElement.getText(),"A");
        Assert.assertEquals(targetElement.getText(),"B");

        Actions actions = new Actions(driver);
        actions.dragAndDrop(sauceElement,targetElement).perform();

        Assert.assertEquals(sauceElement.getText(),"B");
        Assert.assertEquals(targetElement.getText(),"A");
    }
}
