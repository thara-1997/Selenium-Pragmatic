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
}
