package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SauceCheckoutOverviewPage {
    private final WebDriver driver;
    private final By byCartItems = By.xpath("//div[@class='inventory_item_name']");
    private final By byTotal = By.xpath("//div[@class='summary_total_label']");
    private final By byFinishBtn = By.xpath("//button[@id='finish']");

    public SauceCheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
    }
    public List<WebElement> getCartItems(){
        return driver.findElements(byCartItems);
    }

    public int getCartItemCount(){
        return driver.findElements(byCartItems).size();
    }
    public String getTotal(){
        return driver.findElement(byTotal).getText();
    }
    public void clickFinish(){
        driver.findElement(byFinishBtn).click();
    }
}
