package com.pragmatic.selenium.tests;

import com.pragmatic.selenium.pages.NavigationAndLogoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceNavigationAndLogoutTest extends SauceTestBase {
    @Test
    public void testMenuFunctionality() {
       NavigationAndLogoutPage navigationAndLogoutPage = new NavigationAndLogoutPage(driver);
       navigationAndLogoutPage.burgerMenuClick();
       Assert.assertTrue(navigationAndLogoutPage.getMenu(), "The menu is not visible");
    }
    @Test
    public void testVerifyLogoutFunctionality()  {
        NavigationAndLogoutPage navigationAndLogoutPage = new NavigationAndLogoutPage(driver);
        navigationAndLogoutPage.burgerMenuClick();
        navigationAndLogoutPage.clickLogout();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/"));
    }

    @Test
    public void testVerifyAllItems() {
        NavigationAndLogoutPage navigationAndLogoutPage = new NavigationAndLogoutPage(driver);
        navigationAndLogoutPage.burgerMenuClick();
        navigationAndLogoutPage.clickAllItems();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"));

    }

    @Test
    public void testVerifyAboutOption() {
        NavigationAndLogoutPage navigationAndLogoutPage = new NavigationAndLogoutPage(driver);
        navigationAndLogoutPage.burgerMenuClick();
        navigationAndLogoutPage.clickAbout();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://saucelabs.com/"));
    }

}
