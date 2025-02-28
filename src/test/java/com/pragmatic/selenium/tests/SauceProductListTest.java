package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.homework.DataProviderCheckoutProcess;
import com.pragmatic.selenium.pages.SauceProductListPage;
import com.pragmatic.selenium.pages.SauceProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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

    @Test
    public void testStreamForEachForExample(){
        List<WebElement> cartButtons = driver.findElements(By.cssSelector("[data-test = 'inventory-item']"));
        cartButtons.stream().forEach(SauceProductListTest::clickButton);
        SauceProductListPage sauceProductListPage = new SauceProductListPage(driver);
        Assert.assertEquals(sauceProductListPage.getCartNum(), "6","Cart icon num is not matching");
    }

    public static void clickButton(WebElement webElement){
        webElement.findElement(By.tagName("button")).click();
    }
    @Test
    public void testStreamFilterExample(){
        List<WebElement> cartButtons = driver.findElements(By.cssSelector("[data-test = 'inventory-item']"));
        long productStartsWithSauce = cartButtons.stream().filter(webElement -> webElement.findElement(By.cssSelector("[data-test = 'inventory-item-name']")).getText().startsWith("Sauce"))
                .count();
        Assert.assertEquals(productStartsWithSauce, 5);
    }

    @Test
    public void testStreamDisplayDetailsOfEachItem(){
        List<WebElement> inventoryList = driver.findElements(By.cssSelector("[data-test = 'inventory-item']"));
        inventoryList.stream()
                .filter(webElement -> webElement.findElement(By.cssSelector("[data-test = 'inventory-item-name']")).getText().startsWith("Sauce"))
                .map(webElement -> webElement.findElement(By.cssSelector("[data-test = 'inventory-item-name']")).getText())
                .forEach(System.out::println);
    }

    @Test
    public void testStreamSortingItemsInDescendingOrder(){
        List<WebElement> inventoryList = driver.findElements(By.cssSelector("[data-test = 'inventory-item']"));
        inventoryList.stream()
                .filter(webElement -> webElement.findElement(By.cssSelector("[data-test = 'inventory-item-name']")).getText().startsWith("Sauce"))
                .map(webElement -> webElement.findElement(By.cssSelector("[data-test = 'inventory-item-name']")).getText())
                .sorted(Collections.reverseOrder())
                .forEach(System.out::println);
    }

    @Test(dataProviderClass = DataProviderCheckoutProcess.class, dataProvider = "productData")
    public void testAllProducts(String expectedProductName, String expectedDescription, String expectedPrice, String expectedAlt, String expectedImage){
        List<WebElement> inventoryList = driver.findElements(By.cssSelector("[data-test = 'inventory-item']"));
//        Stream<WebElement> product = inventoryList.stream()
//                .filter(webElement -> webElement.findElement(By.cssSelector("[data-test = 'inventory-item-name']")).getText().equals(expectedProductName));
//        Assert.assertEquals(product.count(), 1, "Product" +expectedProductName+  "does not exist");

        long productCountWithGivenPriceAndName = inventoryList.stream()
                .filter(webElement -> isProductExists(expectedProductName,webElement))
                .filter(webElement -> isPriceMatches(expectedPrice,webElement)).count();
        Assert.assertEquals(productCountWithGivenPriceAndName, 1, "Product price does not match");
    }

    private static boolean isPriceMatches(String expectedPrice, WebElement webElement){
        return webElement.findElement(By.cssSelector("[data-test = 'inventory-item-price']")).getText().equals(expectedPrice);
    }
    private static boolean isProductExists(String expectedProductName, WebElement webElement){
        return webElement.findElement(By.cssSelector("[data-test = 'inventory-item-name']")).getText().equals(expectedProductName);
    }
    //2.15
}
