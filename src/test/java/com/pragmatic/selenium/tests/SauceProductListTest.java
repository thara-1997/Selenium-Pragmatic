package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceProductListPage;
import com.pragmatic.selenium.pages.SauceProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SauceProductListTest extends BaseClass{

    @Test
    public void testVerifyProductPage() {
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        SauceProductPage sauceProductPage = new SauceProductPage(driver);
        sauceProductListPage.goToSauceLabsBackPack();
        Assert.assertEquals(sauceProductPage.getProductName(),"Sauce Labs Backpack", "Product name is not matching");
        Assert.assertEquals(sauceProductPage.getProductPrice(),"$29.99","Price is not matching");
    }

    @Test
    public void testClickAddToCartButtonForEachProduct() {
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        for (WebElement addToCartButton : sauceProductListPage.clickCartButtons()) {
            addToCartButton.click();
        }
        Assert.assertEquals(sauceProductListPage.getCartNum(), "6", "Cart Item count is not matching");
    }
}
