package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceCartPage;

import com.pragmatic.selenium.pages.SauceProductListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SauceCartTest extends BaseClass{
    @Test
    public void testVerifyCorrectProductDisplaying() {
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.clickCart();
        Assert.assertEquals(sauceCartPage.getItemDescription(),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "Item Description is not matching");

    }
    @Test
    public void testVerifyMultipleProductsInCart() {
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();
        List<String> expectedCartItems = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");

        for (int i =0; i< sauceCartPage.getCartItemsCount(); i++){
            String cartItemName = sauceCartPage.getCartItems().get(i).getText();
            String expectedProductName = expectedCartItems.get(i);
            Assert.assertEquals(cartItemName,expectedProductName, "Cart Items are not matching");
        }
    }
    @Test
    public void testVerifyRemoveCartItemFunctionality(){
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();
        sauceCartPage.clickRemoveSauceLabs();
        Assert.assertEquals(sauceCartPage.getCartItemsCount(),1,"Cart Item count is not matching");
    }

    @Test
    public void testVerifyContinueShoppingButtonFunctionality() {
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();
        sauceCartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void testVerifyCheckoutFunctionality() {
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();
        sauceCartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-one.html"));
    }

}
