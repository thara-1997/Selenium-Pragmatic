package com.pragmatic.selenium.PageFactoryPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceLoginWithPageFactoryPage {

    @FindBy(id = "user-name")
    WebElement txtUserName;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "login-button")
    WebElement btnLogin;

    @FindBy(css = "h3[data-test='error']")
    WebElement txtError;
    private final WebDriver driver;
    public SauceLoginWithPageFactoryPage(WebDriver driver){

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
    public SauceLoginWithPageFactoryPage typeUsername(String username){
        txtUserName.sendKeys(username);
        return this;
    }
    public SauceLoginWithPageFactoryPage typePassword(String password){
        txtPassword.sendKeys(password);
        return this;
    }
    public void clickLogin(){
        btnLogin.click();
    }
    public String getError(){
        return txtError.getText();
    }
}
