package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SimpleTest {

    @Test
    public void test() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get("https://google.com/ncr");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.name("q")).sendKeys("cats"+ Keys.ENTER);
        WebElement first = wait.until(presenceOfElementLocated(By.cssSelector("h3")));
        webDriver.findElement(By.xpath("//div[@class='MUFPAc']/div[@class='hdtb-mitem']/a[contains(@href , 'CAIQAw')]")).click();

        List<WebElement> imagesList = webDriver.findElements(By.xpath("//div[@class='islrc']//img")); //collect all images in a list
        Assert.assertTrue(imagesList.size()>0);

        File f = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f, new File("src/main/resources/screenshots/screenshot01.png"));
        webDriver.close();
    }
}
