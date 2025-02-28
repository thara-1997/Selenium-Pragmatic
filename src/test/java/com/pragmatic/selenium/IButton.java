package com.pragmatic.selenium;

import org.openqa.selenium.WebDriver;

public interface IButton {

    void click();

    boolean isEnabled();

    boolean isVisible();

    String getText();

    void hover(WebDriver driver);
}
