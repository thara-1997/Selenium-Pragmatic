import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTest {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        //to remove the browser info
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/upload");
    }

    @Test
    public void testFileUpload() {
        String filePath = new File("C:\\Users\\user\\Downloads\\images.jpeg").getAbsolutePath();
        driver.findElement(By.id("file-upload")).sendKeys(filePath);
        driver.findElement(By.id("file-submit")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "File Uploaded!");

    }
}
