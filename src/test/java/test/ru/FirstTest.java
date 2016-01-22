package test.ru;

/**
 * Created by stanislavpavlov on 22/01/16.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class FirstTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
    }

    @Test (description="Launches the Yandex Market site")
    public void gotoMarketYandexRu() {
        driver.get("https://market.yandex.ru/");
        Assert.assertEquals(driver.getTitle(), "Яндекс.Маркет — покупки в тысячах проверенных магазинов");
    }

    @Test (description="Go to Catalog")
    public void gotoCatalog() {
        driver.findElement(By.linkText("Каталог")).click();
        driver.findElement(By.linkText("Популярные товары"));
        driver.findElement(By.linkText("Вас также могут заинтересовать"));
    }

    @Test (description="Go to Electronics -> MobilePhones")
    public void gotoMobilePhones() {
        driver.findElement(By.linkText("Мобильные телефоны")).click();

    }

    @AfterClass
    public void tearDown() {
        // Close all browser windows and safely end the session
        driver.quit();
    }

}
