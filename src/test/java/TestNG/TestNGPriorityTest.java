package TestNG;

import org.testng.annotations.Test;

public class TestNGPriorityTest {

    @Test(priority =0)
    public void testMethod1(){
        System.out.println("TestNGAnnotationTest.PriorityOne");
    }
    @Test(priority = 1)
    public void testMethod2(){
        System.out.println("TestNGAnnotationTest.PriorityTwo");
    }
    @Test(priority = 2)
    public void testMethod3(){
        System.out.println("TestNGAnnotationTest.PriorityThree");
    }
    @Test(priority = 3)
    public void testMethod4(){
        System.out.println("TestNGAnnotationTest.PriorityFour");
    }

}
