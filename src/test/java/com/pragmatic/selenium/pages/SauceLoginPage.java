package com.pragmatic.selenium.pages;

import com.pragmatic.selenium.tests.SauceLoginTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceLoginPage {
    private final WebDriver driver;
    private final By byUserName = By.id("user-name");
    private final By byPassword = By.id("password");
    private final By byLoginButton = By.id("login-button");
    private final By byError = By.cssSelector("h3[data-test='error']");
    private final By byUserNamePlaceholder = By.id("user-name");
    private final By byPasswordPlaceholder = By.id("password");

    public SauceLoginPage(WebDriver driver){
        this.driver = driver;
    }

    public SauceLoginPage typeUserName(String username){
        driver.findElement(byUserName).sendKeys(username);
        return this;
    }

    public SauceLoginPage typePassword(String password){
        driver.findElement(byPassword).sendKeys(password);
        return this;
    }

    public void clickLogin(){
        driver.findElement(byLoginButton).click();
    }

    public String getError(){
        return driver.findElement(byError).getText();
    }

    public SauceLoginPage clearUserName(){
        driver.findElement(byUserName).clear();
        return this;
    }

    public  SauceLoginPage clearPassword(){
        driver.findElement(byPassword).clear();
        return this;
    }



    public String getUsernamePlaceholder(){
        return  driver.findElement(byUserNamePlaceholder).getDomAttribute("placeholder");
    }

    public String getPasswordPlaceHolder(){
        return driver.findElement(byPasswordPlaceholder).getDomAttribute("placeholder");
    }
}
