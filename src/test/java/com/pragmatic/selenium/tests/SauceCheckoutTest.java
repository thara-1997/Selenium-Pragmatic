package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.hw.DataProviderCheckoutProcess;
import com.pragmatic.selenium.pages.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SauceCheckoutTest extends BaseClass {
    @Test(dataProvider = "information-details", dataProviderClass = DataProviderCheckoutProcess.class)
    public void testVerifyInvalidCheckoutInformationFunctionality(String firstname, String lastName, String postalCode, String expectedMessage) {
        SauceCheckoutPage sauceCheckoutPage = new SauceCheckoutPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();
        sauceCartPage.clickCheckout();
        sauceCheckoutPage.typeFirstName(firstname).typeLastName(lastName).typePostalCode(postalCode).clickContinueBtn();
        Assert.assertEquals(sauceCheckoutPage.getErrorMsg(),expectedMessage,"Error message is incorrect");
    }
    @Test
    public void testVerifyValidCheckoutInformationFunctionality() {
       SauceCheckoutPage sauceCheckoutPage = new SauceCheckoutPage(driver);
       SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
       SauceCartPage sauceCartPage = new SauceCartPage(driver);
       sauceProductListPage.addSauceLabsBackPack();
       sauceProductListPage.addSauceLabsBikeLight();
       sauceProductListPage.clickCart();
       sauceCartPage.clickCheckout();
       sauceCheckoutPage.typeFirstName("firstname").typeLastName("lastName").typePostalCode("postalCode").clickContinueBtn();
       Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-two.html"));

    }

    @Test
    public void testVerifyOrderSummaryPage() {
        SauceCheckoutPage sauceCheckoutPage = new SauceCheckoutPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        SauceCheckoutOverviewPage sauceCheckoutOverviewPage = new SauceCheckoutOverviewPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();
        sauceCartPage.clickCheckout();
        sauceCheckoutPage.typeFirstName("firstname").typeLastName("lastName").typePostalCode("postalCode").clickContinueBtn();
        List<String> expectedCartItems = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");
        for (int i =0; i< sauceCheckoutOverviewPage.getCartItemCount(); i++){
            String cartItemName = sauceCheckoutOverviewPage.getCartItems().get(i).getText();
            String expectedProductName = expectedCartItems.get(i);
            Assert.assertEquals(cartItemName,expectedProductName, "Cart Items are not matching");
        }
        Assert.assertEquals(sauceCheckoutOverviewPage.getTotal(),"Total: $43.18", "Total Value is not matching");
    }

    @Test
    public void testVerifyFinishButtonFunctionality() {
        SauceCheckoutPage sauceCheckoutPage = new SauceCheckoutPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        SauceCheckoutOverviewPage sauceCheckoutOverviewPage = new SauceCheckoutOverviewPage(driver);
        SauceFinishMessagePage sauceFinishMessagePage = new SauceFinishMessagePage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();
        sauceCartPage.clickCheckout();
        sauceCheckoutPage.typeFirstName("firstname").typeLastName("lastName").typePostalCode("postalCode").clickContinueBtn();
        sauceCheckoutOverviewPage.clickFinish();
        Assert.assertEquals(sauceFinishMessagePage.getFinishMsg(),"Thank you for your order!","Thank you for your order!");
    }
    @Test
    public void testVerifyCancelButtonFunctionality() {
        SauceCheckoutPage sauceCheckoutPage = new SauceCheckoutPage(driver);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();
        sauceCartPage.clickCheckout();
        sauceCheckoutPage.clickCancelBtn();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/cart.html"));
    }

}
