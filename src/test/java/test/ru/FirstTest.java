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
        // Проверка title
        Assert.assertEquals(driver.getTitle(), "Яндекс.Маркет — покупки в тысячах проверенных магазинов");
        // Нажать по ссылке "Каталог"
        driver.findElement(By.xpath(".//*[@id='index-headline-id-tab-1']/span[1]")).click();
        // Проверка присутствия раздела "Популярные товары"
        Assert.assertEquals(driver.findElement(By.className("main-bestsellers__title")).getText(), "Популярные товары");
        // Перейти по в раздел "Электроника" -> "Мобильные телефоны"
        driver.findElement(By.xpath("html/body/div[1]/div[3]/div[2]/div[2]/div/div[2]/div[1]/a[7]")).click();
        // проверка что в категории "Популярные" и "Новинки" представлены 3 девайса
        List<WebElement> popList = driver.findElement(By.xpath("html/body/div[3]/table/tbody/tr/td[2]/div[1]/ul"))
                                                                                        .findElements(By.tagName("li"));
        Assert.assertEquals(popList.size(), 3);
        List<WebElement> newList = driver.findElement(By.xpath("html/body/div[3]/table/tbody/tr/td[2]/div[2]/ul"))
                                                                                        .findElements(By.tagName("li"));
        Assert.assertEquals(newList.size(), 3);
        // Нажать по "расширенный поиск" блока выбора по параметрам
        driver.findElement(By.xpath("html/body/div[3]/table/tbody/tr/td[2]/table[1]/tbody/tr/td[1]/div/form/table/tbody/tr[6]/td/a")).click();
        // Проверка title
        Assert.assertEquals(driver.getTitle(), "Мобильные телефоны — выбор по параметрам на Яндекс.Маркете");
        // Проверка что справа доступен блок параметров
        Assert.assertEquals(true, driver.findElement(By.className("filter-panel-aside")).isDisplayed());
        // Ввести Цену, руб. "от" значение 5125
        driver.findElement(By.xpath(".//*[@id='gf-pricefrom-var']")).sendKeys("5125");
        // Проверка что Значение успешно проставлено в поле "Цена от"
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='gf-pricefrom-var']")).getAttribute("value"), "5125");
        // Ввести Цену, руб. "до" значение 10123
        driver.findElement(By.xpath(".//*[@id='gf-priceto-var']")).sendKeys("10123");
        // Проверка что Значение успешно проставлено в поле "Цена до"
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='gf-priceto-var']")).getAttribute("value"), "10123");
        // Кликнуть на чекбокс "В продаже"
        driver.findElement(By.xpath(".//*[@id='glf-in-stock-select']")).click();
        // Проверить что Значение чекбокса "В продаже" установлено на "Выбрано"
        Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='glf-in-stock-select']")).isSelected());
        // Раскрыть блок "Платформа"
        driver.findElement(By.xpath("html/body/div[1]/div[4]/div[2]/div[2]/div[1]/div/div[7]/div[1]")).click();
        // Проверка что Успешно раскрыт блок параметра "Платформа"
        Assert.assertEquals(true, driver.findElement(By.xpath("/html/body/div/div[4]/div[2]/div[2]/div[1]/div/div[7]/div[2]")).isDisplayed());
        // Кликнуть на селектбокс "Android"
        driver.findElement(By.xpath(".//*[@id='gf-2142557977--808187701']")).click();
        // Проверка что Значение селектбокса "Android" установлено на "Выбрано"
        Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='gf-2142557977--808187701']")).isSelected());
        // ожидание загрузки элементов
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBePresentInElementLocated(By.className("title")
                                                                , "Мобильные телефоны на Android в Нижнем Новгороде"));
        // собираем все смартфоны в кучу
        List<WebElement> smartphones = driver.findElements(By.className("snippet-card"));
        // выбираем имеющих рейтинг от "3,5" до "4,5"
        ArrayList selectedElem = new ArrayList();
        for (int i = 0; i < smartphones.size(); i++) {
            WebElement one = smartphones.get(i);
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            List<WebElement> elems = one.findElements(By.className("rating"));
            double rating = 0.0;
            if (elems.size() != 0) {
                rating = Double.parseDouble(one.findElement(By.className("rating")).getText());
            }
            if (3.5 <= rating && rating <= 4.5) {
                String telTitle = one.findElement(By.className("snippet-card__header-text")).getText();
                String costTel = one.findElement(By.className("snippet-card__info")).getAttribute("textContent");
                selectedElem.add((i+1) + " - " + telTitle + " - " + costTel);
            }
        }
        // Случано выбрать 3 устройства из представленных на странице, имеющих рейтинг от "3,5" до "4,5",
        // вывести в лог информацию в формате "номер девайса на странице - наименование девайса - стоимость девайса (от-до)"
        List<Integer> list = new ArrayList<Integer>();
        for(int x = 1; x < selectedElem.size(); x++){
            list.add(x);
        }

        Collections.shuffle(list);
        Integer[] randomArray = list.subList(0, 3).toArray(new Integer[3]);

        for(Integer num:randomArray){
            System.out.println(selectedElem.get(num));
        }
    }


    @AfterClass
    public void tearDown() {
        // Close all browser windows and safely end the session
        driver.quit();
    }

}
