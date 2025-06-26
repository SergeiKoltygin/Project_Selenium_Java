package com.example.steps;

import io.qameta.allure.Step;
import com.example.page.ProductFiltrationPage;
import com.example.page.ProductListPage;
import com.example.page.SearchInteractionPage;
import com.example.utils.CustomAssertions;

import java.util.List;

/**
 * Класс шагов для взаимодействия с поиском товаров на сайте.
 *
 * @author Колтыгин Сергей
 */
public class SearchInteractionSteps {

    /** Страница поиска товаров */
    private SearchInteractionPage searchPage;

    /** Страница фильтрации товаров */
    private ProductFiltrationPage filtrationPage;

    private ProductListPage productListPage;

    /** Название первого найденного товара */
    private String savedProductTitle;

    /**
     * Конструктор с инициализацией страниц поиска и фильтрации.
     *
     * @param searchPage объект страницы поиска
     * @param filtrationPage объект страницы фильтрации
     */
    public SearchInteractionSteps(SearchInteractionPage searchPage, ProductFiltrationPage filtrationPage, ProductListPage productListPage) {
        this.searchPage = searchPage;
        this.filtrationPage = filtrationPage;
        this.productListPage = productListPage;
    }

    /**
     * Сохраняет название первого товара на текущей странице.
     */
    public void saveFirstProductTitle() {
        productListPage.waitForLoadingSpinnerToDisappear();  // Ожидание загрузки
        List<String> titles = productListPage.getAllTitlesOnPage();
        savedProductTitle = titles.get(0);
    }

    /**
     * Выполняет поиск по сохраненному названию товара.
     */
    @Step("Ввести сохранённое наименование товара в поиск")
    public void searchSavedProduct() {
        searchPage.enterSearchQuery(savedProductTitle);
        searchPage.clickSearchButton();
        productListPage.waitForLoadingSpinnerToDisappear();
    }

    /**
     * Проверяет, что сохраненный товар найден в результатах поиска.
     */
    @Step("Проверить, что искомый товар найден среди результатов")
    public void verifySearchedProductIsInResults() {
        List<String> titles = productListPage.getAllTitlesOnPage();
        CustomAssertions.assertThat(titles.stream().anyMatch(title -> title.contains(savedProductTitle.toLowerCase())),
                "Товар не найден в результатах поиска: " + savedProductTitle);
    }
}

