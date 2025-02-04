package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.homework.DataProviderCheckoutProcess;
import com.pragmatic.selenium.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SauceCheckoutTest extends SauceTestBase {

    public void addProducts(){
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.addSauceLabsBackPack();
        sauceProductListPage.addSauceLabsBikeLight();
        sauceProductListPage.clickCart();

    }
    public void clickCheckoutBtn(){
        SauceCartPage sauceCartPage = new SauceCartPage(driver);
        sauceCartPage.clickCheckout();
    }

    public void enterCheckoutDetails(){
        SauceCheckoutPage sauceCheckoutPage = new SauceCheckoutPage(driver);
        sauceCheckoutPage.typeFirstName("firstname").typeLastName("lastName").typePostalCode("postalCode").clickContinueBtn();
    }


    @Test(dataProvider = "information-details", dataProviderClass = DataProviderCheckoutProcess.class)
    public void testVerifyInvalidCheckoutInformationFunctionality(String firstname, String lastName, String postalCode, String expectedMessage) {
        addProducts();
        clickCheckoutBtn();

        SauceCheckoutPage sauceCheckoutPage = new SauceCheckoutPage(driver);
        sauceCheckoutPage.typeFirstName(firstname).typeLastName(lastName).typePostalCode(postalCode).clickContinueBtn();
        Assert.assertEquals(sauceCheckoutPage.getErrorMsg(),expectedMessage,"Error message is incorrect");
    }
    @Test
    public void testVerifyValidCheckoutInformationFunctionality() {
       addProducts();
       clickCheckoutBtn();
       enterCheckoutDetails();

       Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-two.html"));

    }

    @Test
    public void testVerifyOrderSummaryPage() {

        addProducts();
        clickCheckoutBtn();
        enterCheckoutDetails();

        SauceCheckoutOverviewPage sauceCheckoutOverviewPage = new SauceCheckoutOverviewPage(driver);
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
        addProducts();
        clickCheckoutBtn();
        enterCheckoutDetails();

        SauceCheckoutOverviewPage sauceCheckoutOverviewPage = new SauceCheckoutOverviewPage(driver);
        sauceCheckoutOverviewPage.clickFinish();

        SauceFinishMessagePage sauceFinishMessagePage = new SauceFinishMessagePage(driver);
        Assert.assertEquals(sauceFinishMessagePage.getFinishMsg(),"Thank you for your order!","Thank you for your order!");
    }
    @Test
    public void testVerifyCancelButtonFunctionality() {
        addProducts();
        clickCheckoutBtn();

        SauceCheckoutPage sauceCheckoutPage = new SauceCheckoutPage(driver);
        sauceCheckoutPage.clickCancelBtn();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/cart.html"));
    }

}
