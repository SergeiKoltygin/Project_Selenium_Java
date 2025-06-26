package com.example.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.utils.Properties;

import java.time.Duration;


/**
 * Страница для фильтрации товаров.
 *
 * @author Колтыгин Сергей
 */
public class ProductFiltrationPage {
    /**
     * Веб-драйвер для работы с браузером.
     */
    private WebDriver chromeDriver;

    /**
     * Ожидание для синхронизации с элементами.
     */
    private WebDriverWait wait;

    /**
     * Поле для ввода минимальной цены.
     */
    private WebElement minInput;

    /**
     * Поле для ввода максимальной цены.
     */
    private WebElement maxInput;

    /**
     * Кнопка "Показать всё" для отображения полного списка производителей.
     */
    private WebElement showAllManufacturers;

    /**
     * Поле поиска производителя в списке фильтров.
     */
    private WebElement searchInput;

    /**
     * Конструктор класса ProductFiltrationPage.
     *
     * @param chromeDriver WebDriver для работы с браузером.
     */
    public ProductFiltrationPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(Properties.testsProperties.defaultTimeout()));
    }


    /**
     * Устанавливает диапазон цен для фильтрации товаров.
     *
     * @param min минимальная цена.
     * @param max максимальная цена.
     */
    public void setPriceRange(int min, int max) {
        minInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='range-filter-field-glprice_25563_min']")));
        maxInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='range-filter-field-glprice_25563_max']")));
        minInput.sendKeys(String.valueOf(min));
        maxInput.sendKeys(String.valueOf(max));
    }

    /**
     * Устанавливает фильтрацию товаров по указанным брендам.
     * Открывает список производителей, ищет по имени и устанавливает чекбоксы
     *
     * @param brands массив строк с названиями брендов (например, "HP", "Lenovo").
     */
    public void setBrand(String... brands) {
        WebDriverWait shortWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(2));
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;

        showAllManufacturers = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text() = 'Показать всё']")));
        showAllManufacturers.click();

        for (String brand : brands) {
            searchInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='Найти']")));

            js.executeScript("arguments[0].value = '';", searchInput);

            shortWait.until(driver -> searchInput.getAttribute("value").isEmpty());

            searchInput.sendKeys(brand);

            shortWait.until(driver -> searchInput.getAttribute("value").equalsIgnoreCase(brand));

            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='" + brand + "']/preceding-sibling::*")));
            checkbox.click();

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

