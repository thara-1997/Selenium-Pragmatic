package com.pragmatic.selenium.pageFactoryPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceLoginWithPageFactoryPage {

    private final WebDriver driver;
    @FindBy(id = "user-name")
    private WebElement txtUserName;
    @FindBy(id = "password")
    private WebElement txtPassword;
    @FindBy(id = "login-button")
    private WebElement btnLogin;
    @FindBy(css = "h3[data-test='error']")
    private WebElement txtError;

    @FindBy(id = "user-name")
    private WebElement userNamePlaceholder;

    @FindBy(id = "password")
    private WebElement passwordPlaceholder;
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

    public String getUsernamePlaceholder(){
        return userNamePlaceholder.getDomAttribute("placeholder");
    }
    public String getPasswordPlaceholder(){
        return passwordPlaceholder.getDomAttribute("placeholder");
    }
}
