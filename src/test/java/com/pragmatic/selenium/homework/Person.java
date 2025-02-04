package com.pragmatic.selenium.homework;

import net.datafaker.Faker;

public class Person {
   Faker faker;

   public Person(){
       faker = new Faker();
   }

   public String getFirstName(){
       return faker.name().firstName();
   }

   public String getLastName(){
       return faker.name().lastName();
   }

   public String getPostalCode(){
       return faker.address().postcode();
   }

}
