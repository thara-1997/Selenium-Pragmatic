package testNG;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGDependOnMethod {
    @Test
    @Parameters({"browser","headless"})
    public void testMethod1(@Optional("chrome") String browser, @Optional("false") String headless){
        System.out.println("browser: " +browser);
        System.out.println("headless = " +headless);
        System.out.println("TestNGAnnotationTest.PriorityOne");
    }
    @Test(dependsOnMethods = {"testMethod3", "testMethod4"})
    public void testMethod2(){
        System.out.println("TestNGAnnotationTest.PriorityTwo");
    }
    @Test(groups = {"regression"})
    public void testMethod3(){
        System.out.println("TestNGAnnotationTest.PriorityThree");
    }
    @Test
    public void testMethod4(){
        System.out.println("TestNGAnnotationTest.PriorityFour");
    }
}
