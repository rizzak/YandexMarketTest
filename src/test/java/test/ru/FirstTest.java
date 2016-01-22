package test.ru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    }

    @Test
    public void gotoMarketYandexRu() {
        driver.get("https://market.yandex.ru/");
        Assert.assertEquals(driver.getTitle(), "Яндекс.Маркет — покупки в тысячах проверенных магазинов");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Каталог")).click();
    }


    @AfterClass
    public void tearDown() {
        // Close all browser windows and safely end the session
        driver.quit();
    }

}
