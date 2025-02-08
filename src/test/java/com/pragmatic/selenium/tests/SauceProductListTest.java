package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.SauceProductListPage;
import com.pragmatic.selenium.pages.SauceProductPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceProductListTest extends SauceTestBase {

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

    @Test
    public void testRemoveButtonFunctionality() {
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        for (WebElement addToCartButton : sauceProductListPage.clickCartButtons()) {
            addToCartButton.click();
        }

        for (WebElement removeBtn: sauceProductListPage.clickRemoveBtn()){
            removeBtn.click();
        }

        for (WebElement product: sauceProductListPage.getAllProductDetails()){
            Assert.assertTrue(product.getText().contains("Add to cart"), "validation is not working");
        }
    }

    @Test(groups = {"sanity"})
    public void testFilterProductsAtoZ(){
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.selectFilter("az");

        String previousProduct = "";
        for (WebElement product: sauceProductListPage.getProductDetails()){
            String currentProduct = product.getText();
            Assert.assertTrue(currentProduct.compareTo(previousProduct)>=0, "Products are not sorted in ascending order (A to Z). Current: "
            +currentProduct+ "Previous product: " +previousProduct);
            previousProduct = currentProduct;
        }
    }

    @Test
    public void testFilterProductsZtoA(){
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.selectFilter("za");

        String previousProduct = sauceProductListPage.getProductDetails().get(0).getText();
        for (WebElement product: sauceProductListPage.getProductDetails()){
            String currentProduct = product.getText();
            Assert.assertTrue(currentProduct.compareTo(previousProduct)<=0,"Products are not sorted in descending order (Z to A). Current: " +currentProduct+
                    "Previous: " +previousProduct);
            previousProduct = currentProduct;
        }
    }
    @Test
    public void testFilterPriceLowToHigh(){
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.selectFilter("lohi");

        double previousValue = 0.0;
        for(WebElement price: sauceProductListPage.getPriceList()){
            String priceText = price.getText().replace("$","").trim();
            double currentValue = Double.parseDouble(priceText);
            Assert.assertTrue(currentValue>=previousValue, "Products are not sorted in ascending order (low to high) Current Price: " +currentValue+
                    "previous: " +previousValue);
            previousValue = currentValue;
        }
    }

    @Test
    public void testFilterPriceHighToLow(){
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        sauceProductListPage.selectFilter("hilo");

        double previousValue = Double.MAX_VALUE;
        for (WebElement price: sauceProductListPage.getPriceList()){
            String priceText = price.getText().replace("$","").trim();
            double currentValue = Double.parseDouble(priceText);
            Assert.assertTrue(currentValue<=previousValue, "Products are not sorted in descending order (high to low). Current Price: " +currentValue+
                    "previous: " +previousValue);
            previousValue = currentValue;
        }
    }
}
