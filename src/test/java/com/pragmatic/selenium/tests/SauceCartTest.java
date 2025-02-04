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
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.clickCart();

        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        Assert.assertEquals(sauceCartPage.getItemDescription(),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "Item Description is not matching");

    }

    public void addProductsToCart(){
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();
    }
    @Test
    public void testVerifyMultipleProductsInCart() {
        addProductsToCart();

        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        List<String> expectedCartItems = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");
        for (int i =0; i< sauceCartPage.getCartItemsCount(); i++){
            String cartItemName = sauceCartPage.getCartItems().get(i).getText();
            String expectedProductName = expectedCartItems.get(i);
            Assert.assertEquals(cartItemName,expectedProductName, "Cart Items are not matching");
        }
    }
    @Test
    public void testVerifyRemoveCartItemFunctionality(){
        addProductsToCart();

        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        sauceCartPage.clickRemoveSauceLabs();
        Assert.assertEquals(sauceCartPage.getCartItemsCount(),1,"Cart Item count is not matching");
    }

    @Test
    public void testVerifyContinueShoppingButtonFunctionality() {
        addProductsToCart();

        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        sauceCartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void testVerifyCheckoutFunctionality() {
        addProductsToCart();

        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        sauceCartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-one.html"));
    }

}
