package com.pragmatic.selenium.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByPartialText extends By {
    private final String partialText;

    public ByPartialText(String partialText){
        this.partialText = partialText;
    }
    @Override
    public List<WebElement> findElements(SearchContext driver){
        String xpath = String.format("//*[contains(text(), '%s']", partialText);
        System.out.println("xpath = " +xpath);
        return driver.findElements(By.xpath(xpath));
    }
}
