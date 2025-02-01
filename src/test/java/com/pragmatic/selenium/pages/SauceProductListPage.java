package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SauceProductListPage {
    private final WebDriver driver;

    By byTitle = By.cssSelector("[data-test='title']");

    By bySauceLabsBackPackCartBtn = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
    By bySauceLabsBikeLightBtn = By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']");
    By byClickCart = By.xpath("//a[@class='shopping_cart_link']");
    By bySauceLabsBackPack = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");
    By byCartBtn = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']");
    By byCartIconNum = By.xpath("//span[@class='shopping_cart_badge']");

    public SauceProductListPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(){
        return driver.findElement(byTitle).getText();
    }
    public void addSauceLabsBackPack(){
        driver.findElement(bySauceLabsBackPackCartBtn).click();
    }

    public void addSauceLabsBikeLight(){
        driver.findElement(bySauceLabsBikeLightBtn).click();
    }

    public void clickCart(){
        driver.findElement(byClickCart).click();
    }

    public void goToSauceLabsBackPack(){
        driver.findElement(bySauceLabsBackPack).click();
    }
    public List<WebElement> clickCartButtons(){
        return driver.findElements(byCartBtn);
    }

    public String getCartNum(){
        return driver.findElement(byCartIconNum).getText();
    }
}
