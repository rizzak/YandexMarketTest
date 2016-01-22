package test.ru;

import org.apache.xpath.SourceTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private WebDriver driver;

    @Test
    public void gotoMarketYandexRu() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Перейти по адресу https://market.yandex.ru/
        driver.get("https://market.yandex.ru/");
        // Развернуть браузер для наглядности
        driver.manage().window().maximize();
        // Check title
        Assert.assertEquals(driver.getTitle(), "Яндекс.Маркет — покупки в тысячах проверенных магазинов");
        // Нажать по ссылке "Каталог"
        driver.findElement(By.xpath(".//*[@id='index-headline-id-tab-1']/span[1]")).click();
        // Перейти по в раздел "Электроника" -> "Мобильные телефоны"
        driver.findElement(By.xpath("html/body/div[1]/div[3]/div[2]/div[2]/div/div[2]/div[1]/a[7]")).click();
        // Нажать по "расширенный поиск" блока выбора по параметрам
        driver.findElement(By.xpath("html/body/div[3]/table/tbody/tr/td[2]/table[1]/tbody/tr/td[1]/div/form/table/tbody/tr[6]/td/a")).click();
        // Ввести Цену, руб. "от" значение 5125
        driver.findElement(By.xpath(".//*[@id='gf-pricefrom-var']")).sendKeys("5125");
        // Ввести Цену, руб. "до" значение 10123
        driver.findElement(By.xpath(".//*[@id='gf-priceto-var']")).sendKeys("10123");
        // Кликнуть на чекбокс "В продаже"
        driver.findElement(By.xpath(".//*[@id='glf-in-stock-select']")).click();
        // Раскрыть блок "Платформа"
        driver.findElement(By.xpath("html/body/div[1]/div[4]/div[2]/div[2]/div[1]/div/div[7]/div[1]")).click();
        // Кликнуть на селектбокс "Android"
        driver.findElement(By.xpath(".//*[@id='gf-2142557977--808187701']")).click();
        // ожидание загрузки элементов
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBePresentInElementLocated(By.className("title"), "Мобильные телефоны на Android в Нижнем Новгороде"));
        // собираем все смартфоны в кучу
        List<WebElement> smartphones = driver.findElements(By.className("snippet-card"));
        // выбираем имеющих рейтинг от "3,5" до "4,5"
        for (WebElement one: smartphones) {
            System.out.println("Num: " + 1 + " Rating: " + one.findElement(By.className("rating")).getText());
            System.out.println("-----------------------------------");
        }
        // Случано выбрать 3 устройства из представленных на странице, имеющих рейтинг от "3,5" до "4,5",
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i < smartphones.size(); i++){
            list.add(i);
        }

        Collections.shuffle(list);
        Integer[] randomArray = list.subList(0, 3).toArray(new Integer[3]);

        for(Integer num:randomArray){
            System.out.println(num);
        }

        // вывести в лог информацию в формате "номер девайса на странице - наименование девайса - стоимость девайса (от-до)"
        for (WebElement one: smartphones) {
            System.out.println(one.getAttribute("title"));
        }

    }


    @AfterClass
    public void tearDown() {
        // Close all browser windows and safely end the session
        driver.quit();
    }

}
