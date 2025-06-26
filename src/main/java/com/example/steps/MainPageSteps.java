package com.example.steps;

import io.qameta.allure.Step;
import com.example.page.MainPage;

/**
 * Класс шагов для главной страницы сайта.
 *
 * @author Колтыгин Сергей
 */
public class MainPageSteps {
    /** Главная страница сайта */
    private MainPage mainPage;

    /**
     * Конструктор для инициализации главной страницы.
     *
     * @param mainPage объект главной страницы
     */
    public MainPageSteps(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    /**
     * Нажимает на кнопку "Каталог" на главной странице.
     */
    @Step("Нажать на кнопку 'Каталог'")
    public void clickCatalogButton() {
        mainPage.clickCatalogButton();
    }

    /**
     * Наводит курсор на раздел "Электроника" в каталоге.
     */
    @Step("Навести курсор на раздел 'Электроника'")
    public void hoverOnElectronics() {
        mainPage.hoverOnElectronics();
    }

    /**
     * Переходит в раздел "Ноутбуки" в каталоге.
     */
    @Step("Перейти в раздел 'Ноутбуки'")
    public void clickLaptops() {
        mainPage.clickLaptops();
    }
}

