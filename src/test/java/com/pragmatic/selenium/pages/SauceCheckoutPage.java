package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SauceCheckoutPage {
    private final WebDriver driver;
    By byFirstName = By.xpath("//input[@id='first-name']");
    By byLastName = By.xpath("//input[@id='last-name']");
    By byPostalCode = By.xpath("//input[@id='postal-code']");
    By byContinueBTN = By.xpath("//input[@id='continue']");
    By byErrorMessage = By.xpath("//div[@class='error-message-container error']");
    By byCancelBtn = By.xpath("//button[@id='cancel']");

    public SauceCheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public SauceCheckoutPage typeFirstName(String firstName){
        driver.findElement(byFirstName).sendKeys(firstName);
        return this;
    }

    public SauceCheckoutPage typeLastName(String lastName){
        driver.findElement(byLastName).sendKeys(lastName);
        return this;
    }

    public SauceCheckoutPage typePostalCode(String postalCode){
        driver.findElement(byPostalCode).sendKeys(postalCode);
        return this;
    }
    public void clickContinueBtn(){
        driver.findElement(byContinueBTN).click();
    }

    public String getErrorMsg(){
        return driver.findElement(byErrorMessage).getText();
    }


    public void clickCancelBtn(){
        driver.findElement(byCancelBtn).click();
    }


}
