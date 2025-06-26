package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.utils.Properties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


/**
 * Представляющий список товаров на странице Яндекс.Маркета.
 * Предоставляет методы для получения информации о товарах, их названиях, ценах и для взаимодействия с прокруткой страницы.
 * Используется после применения фильтров для проверки соответствия отображаемых товаров заданным условиям.
 *
 * @author Колтыгин Сергей
 */
public class ProductListPage {
    /**
     * Веб-драйвер для работы с браузером.
     */
    private WebDriver chromeDriver;
    /**
     * Ожидание для синхронизации с элементами.
     */
    private WebDriverWait wait;

    /**
     * Конструктор класса ProductFiltrationPage.
     *
     * @param chromeDriver WebDriver для работы с браузером.
     */
    public ProductListPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(Properties.testsProperties.defaultTimeout()));
    }

    /**
     * Получает количество товаров на текущей странице.
     *
     * @return количество товаров на странице.
     */
    public int getNumberOfProductsOnPage() {
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@data-auto-themename='listDetailed']")));
        return products.size();
    }

    /**
     * Получает все цены товаров на текущей странице.
     *
     * @return список всех цен товаров на странице.
     */
    public List<Long> getAllPricesOnPage() {
        List<WebElement> priceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@data-auto-themename='listDetailed']//span[@data-auto='snippet-price-current']")
        ));

        List<Long> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replaceAll("[^\\d]", "");
            prices.add(Long.parseLong(priceText));
        }
        return prices;
    }

    /**
     * Получает все наименования товаров на текущей странице.
     *
     * @return список наименований товаров на странице.
     */
    public List<String> getAllTitlesOnPage() {
        List<WebElement> titleElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@data-auto-themename='listDetailed']//div[@data-baobab-name='title']")
        ));
        List<String> titles = new ArrayList<>();
        for (WebElement titleElement : titleElements) {
            titles.add(titleElement.getText().toLowerCase());
        }

        return titles;
    }

    /**
     * Ожидает, пока исчезнет индикатор загрузки.
     */
    public void waitForLoadingSpinnerToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@data-auto, 'searchFiltersV2Loading') or contains(@data-auto, 'Spinner')]")
        ));
    }

    /**
     * Прокручивает страницу вниз, чтобы загрузить все товары.
     */
    public void scrollToLoadAllProducts() {
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        int previousProductCount = 0;

        while (true) {
            List<WebElement> products = chromeDriver.findElements(
                    By.xpath("//div[@data-auto-themename='listDetailed']"));
            int currentProductCount = products.size();

            if (currentProductCount == previousProductCount) {
                break;
            }

            previousProductCount = currentProductCount;

            WebElement lastProduct = products.get(products.size() - 1);
            js.executeScript("arguments[0].scrollIntoView(true);", lastProduct);

            waitForLoadingSpinnerToDisappear();

        }
    }

    /**
     * Прокручивает страницу вверх.
     */
    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript("window.scrollTo(0, 0);");
    }

}

