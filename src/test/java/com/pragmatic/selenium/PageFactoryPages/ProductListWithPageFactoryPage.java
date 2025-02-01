package com.pragmatic.selenium.PageFactoryPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductListWithPageFactoryPage {

    @FindBy(css = "[data-test='title']")
    WebElement title;

    private final WebDriver driver;
     public ProductListWithPageFactoryPage(WebDriver driver){
         this.driver = driver;

         PageFactory.initElements(driver, this);
     }
     public String getTitle(){
         return title.getText();
     }
}
