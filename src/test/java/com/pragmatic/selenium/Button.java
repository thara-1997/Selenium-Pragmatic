package com.pragmatic.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Actions;

public class Button implements WrapsElement, IButton{

    private final WebElement btnElement;
    private final WebDriver driver;

    public Button(WebElement btnElement, WebDriver driver){
        this.btnElement =btnElement;
        this.driver = driver;
    }

    @Override
    public void click(){
        btnElement.click();
    }
    @Override
    public boolean isEnabled(){
        return btnElement.isEnabled();
    }

    @Override
    public boolean isVisible(){
        return btnElement.isDisplayed();
    }

    @Override
    public String getText(){
        return btnElement.getDomAttribute("value");
    }
    @Override
    public WebElement getWrappedElement(){
        return btnElement;
    }

    @Override
    public void hover(WebDriver driver){
       Actions actions = new Actions(driver);
       actions.moveToElement(btnElement).perform();
    }

}
