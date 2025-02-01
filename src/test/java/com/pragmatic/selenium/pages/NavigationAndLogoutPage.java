package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationAndLogoutPage {
    private final WebDriver driver;
    By byBurgerMenu = By.xpath("//button[@id='react-burger-menu-btn']");
    By byMenuItem = By.xpath("//div[@class='bm-menu-wrap']");
    By byLogout = By.id("logout_sidebar_link");
    By byAllItems = By.xpath("//a[@id='inventory_sidebar_link']");
    By byAbout = By.xpath("//a[@id='about_sidebar_link']");

    public NavigationAndLogoutPage(WebDriver driver){
        this.driver = driver;
    }
    public void burgerMenuClick(){
     driver.findElement(byBurgerMenu).click();
    }
    public boolean getMenu(){
        return driver.findElement(byMenuItem).isDisplayed();
    }
    public void clickLogout(){
        WebElement logout = driver.findElement(byLogout);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        logout.click();
    }
    public void clickAllItems(){
        WebElement allItems = driver.findElement(byAllItems);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(allItems));
        allItems.click();
    }
    public void clickAbout(){
        WebElement about = driver.findElement(byAbout);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(about));
        about.click();
    }

}
