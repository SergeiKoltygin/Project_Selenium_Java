package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Страница главной страницы с методами для взаимодействия с элементами на странице.
 *
 * @author Колтыгин Сергей
 */

public class MainPage {
    /**
     * Веб-драйвер для работы с браузером.
     */
    private WebDriver chromeDriver;
    /**
     * Экшен для выполнения действий с элементами на странице.
     */
    private Actions actions;
    /**
     * Ожидание для синхронизации с элементами.
     */
    private WebDriverWait wait;
    /**
     * Кнопка открытия каталога.
     */
    private WebElement catalogMenu;
    /**
     * Элемент "Электроника" для наведения.
     */
    private WebElement electronics;
    /**
     * Кнопка "Ноутбуки" для клика.
     */
    private WebElement laptops;

    /**
     * Конструктор класса MainPage.
     *
     * @param chromeDriver WebDriver для работы с браузером.
     */
    public MainPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.actions = new Actions(chromeDriver);
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));
    }

    /**
     * Нажимает на кнопку "Каталог".
     */
    public void clickCatalogButton() {
        catalogMenu = wait.
                until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Каталог']")));
        catalogMenu.click();
    }

    /**
     * Наводит курсор на раздел "Электроника".
     */
    public void hoverOnElectronics() {
        electronics = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-zone-name='catalog-content']//li[contains(., 'Электроника')]")));
        actions.moveToElement(electronics).perform();
    }

    /**
     * Кликает по кнопке "Ноутбуки".
     */
    public void clickLaptops() {
        laptops = wait.
                until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Ноутбуки']")));
        laptops.click();
    }
}

