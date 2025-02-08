package testNG;

import org.testng.annotations.Test;

public class TestNGDependOnMethod {
    @Test
    public void testMethod1(){
        System.out.println("TestNGAnnotationTest.PriorityOne");
    }
    @Test(dependsOnMethods = {"testMethod3", "testMethod4"})
    public void testMethod2(){
        System.out.println("TestNGAnnotationTest.PriorityTwo");
    }
    @Test
    public void testMethod3(){
        System.out.println("TestNGAnnotationTest.PriorityThree");
    }
    @Test
    public void testMethod4(){
        System.out.println("TestNGAnnotationTest.PriorityFour");
    }
}
