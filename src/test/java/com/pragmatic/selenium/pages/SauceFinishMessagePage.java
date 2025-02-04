package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceFinishMessagePage {
    private final WebDriver driver;
    private final By byFinishMsg = By.xpath("//h2[normalize-space()='Thank you for your order!']");

    public SauceFinishMessagePage(WebDriver driver){
        this.driver = driver;
    }
    public String getFinishMsg(){
        return driver.findElement(byFinishMsg).getText();
    }
}
