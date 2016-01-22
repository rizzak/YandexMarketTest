package test.ru;

import org.apache.xpath.SourceTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        //driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void gotoMarketYandexRu() {
        driver.get("https://market.yandex.ru/");
        // Check title
        Assert.assertEquals(driver.getTitle(), "Яндекс.Маркет — покупки в тысячах проверенных магазинов");
        // goto Catalog
        driver.findElement(By.xpath(".//*[@id='index-headline-id-tab-1']/span[1]")).click();
        for(WebElement x : (driver.findElements(By.tagName("h1")))) {
            if (x.getText().equals("Популярные товары")){
                System.out.println("good");
            }
            else{
                System.out.printf("Bad");
            }
        }
    }


    @AfterClass
    public void tearDown() {
        // Close all browser windows and safely end the session
        driver.quit();
    }

}
