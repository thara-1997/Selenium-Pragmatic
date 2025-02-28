package com.pragmatic.selenium.homework;

import org.testng.annotations.DataProvider;

public class DataProviderCheckoutProcess {
    @DataProvider(name ="information-details")
    public Object[][] userCredentials(){
        return new Object[][]{
                {"","","","Error: First Name is required"} ,
                {"Thathsarani", "","","Error: Last Name is required"},
                {"Thathsarani","Sudusinghe","","Error: Postal Code is required"},
                {"Thathsarani", "", "82100", "Error: Last Name is required"}

        };
    }

    @DataProvider(name = "productData")
    public Object[][] getProductTestData() {
        return new Object[][] {
                { "Sauce Labs Backpack",
                        "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                        "$29.99", "Sauce Labs Backpack", ".*/static/media/sauce-backpack.*\\.jpg$" },

                { "Sauce Labs Bike Light",
                        "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                        "$9.99", "Sauce Labs Bike Light", ".*/static/media/bike-light.*\\.jpg$" }
        };
    }
}
