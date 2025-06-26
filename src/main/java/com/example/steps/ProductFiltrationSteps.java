package com.example.steps;

import io.qameta.allure.Step;
import com.example.page.ProductFiltrationPage;
import com.example.page.ProductListPage;

/**
 * Используется в автотестах для установки диапазона цен и фильтрации по производителям.
 * Объединяет действия над фильтрационной панелью и ожидание загрузки отфильтрованных результатов.
 *
 * @author Колтыгин Сергей
 */
public class ProductFiltrationSteps {
    /**
     * Страница фильтрации товаров
     */
    private ProductFiltrationPage filtrationPage;
    private ProductListPage productListPage;

    /**
     * Конструктор для инициализации страницы фильтрации.
     *
     * @param filtrationPage страница фильтрации товаров
     * @param productListPage страница со списком товаров
     *
     */
    public ProductFiltrationSteps(ProductFiltrationPage filtrationPage, ProductListPage productListPage) {
        this.filtrationPage = filtrationPage;
        this.productListPage = productListPage;
    }

    /**
     * Устанавливает диапазон цен для фильтрации товаров.
     *
     * @param min минимальная цена
     * @param max максимальная цена
     */
    @Step("Установить диапазон цен от {min} до {max}")
    public void setPriceRange(int min, int max) {
        filtrationPage.setPriceRange(min, max);
    }

    /**
     * Выбирает производителей для фильтрации товаров.
     *
     * @param brands список брендов для фильтрации
     */
    @Step("Выбрать производителей: {brands}")
    public void selectManufacturers(String... brands) {
        filtrationPage.setBrand(brands);
        productListPage.waitForLoadingSpinnerToDisappear();
    }
}
