package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceProductPage {
    private final WebDriver driver;
    private final By byProductName = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");
    private final By byProductPrice = By.xpath("//div[@class='inventory_details_price']");

    public SauceProductPage(WebDriver driver){
        this.driver = driver;
    }

    public String getProductName(){
        return driver.findElement(byProductName).getText();
    }
    public String getProductPrice(){
        return driver.findElement(byProductPrice).getText();
    }
}
