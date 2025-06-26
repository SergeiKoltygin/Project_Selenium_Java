package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.utils.Properties;

import java.time.Duration;

/**
 * Страница взаимодействия с поисковым запросом на Яндекс.Маркете.
 *
 * @author Колтыгин Сергей
 */
public class SearchInteractionPage {

    /**
     * Веб-драйвер для работы с браузером.
     */
    private WebDriver chromeDriver;
    /**
     * Ожидание для синхронизации с элементами.
     */
    private WebDriverWait wait;
    /**
     * Поле ввода для поискового запроса.
     */
    private WebElement input;
    /**
     * Кнопка поиска.
     */
    private WebElement searchBtn;

    /**
     * Конструктор класса SearchInteractionPage.
     *
     * @param chromeDriver WebDriver для работы с браузером.
     */
    public SearchInteractionPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(Properties.testsProperties.defaultTimeout()));
    }

    /**
     * Вводит поисковой запрос в поле поиска.
     *
     * @param query строка поискового запроса.
     */
    public void enterSearchQuery(String query) {
        input = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='Найти товары']")));
        input.clear();
        input.sendKeys(query);
    }

    /**
     * Кликает по кнопке поиска.
     */
    public void clickSearchButton() {
        searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Найти']")));
        searchBtn.click();
    }
}

