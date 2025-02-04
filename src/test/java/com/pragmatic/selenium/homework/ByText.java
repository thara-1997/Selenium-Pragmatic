package com.pragmatic.selenium.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByText extends By {
    private final String exactText;
    public ByText(String exactText){
        this.exactText = exactText;
    }

    @Override
    public List<WebElement> findElements(SearchContext driver) {
        String xpath = String.format("//*[text()='%s']", exactText);
        System.out.println("xpath = " +xpath);
        return driver.findElements(By.xpath(xpath));
    }
}
