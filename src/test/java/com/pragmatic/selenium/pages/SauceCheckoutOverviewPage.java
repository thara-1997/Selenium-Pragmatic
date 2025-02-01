package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SauceCheckoutOverviewPage {
    private final WebDriver driver;
    By byCartItems = By.xpath("//div[@class='inventory_item_name']");
    By byTotal = By.xpath("//div[@class='summary_total_label']");
    By byFinishBtn = By.xpath("//button[@id='finish']");

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
