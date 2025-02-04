package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class SauceCartPage {
    private final WebDriver driver;

    private final By byItemDescription = By.xpath("//div[@class='inventory_item_desc']");
    private final By byCartItems = By.xpath("//div[@class='inventory_item_name']");
    private final By byRemoveBackPack = By.xpath("//button[@id='remove-sauce-labs-backpack']");
    private final By byContinueBtn = By.xpath("//button[@id='continue-shopping']");
    private final By byCheckoutBtn = By.xpath("//button[@id='checkout']");

    public SauceCartPage(WebDriver driver){
        this.driver = driver;
    }

    public String getItemDescription(){
        return driver.findElement(byItemDescription).getText();
    }

   public List<WebElement> getCartItems(){
        return driver.findElements(byCartItems);
   }
   public void clickRemoveSauceLabs(){
        driver.findElement(byRemoveBackPack).click();
   }

   public int getCartItemsCount(){
        return driver.findElements(byCartItems).size();
   }

   public void clickContinueShopping(){
        driver.findElement(byContinueBtn).click();
   }

   public void clickCheckout(){
        driver.findElement(byCheckoutBtn).click();
   }

}
