package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import com.example.page.MainPage;
import com.example.page.ProductFiltrationPage;
import com.example.page.ProductListPage;
import com.example.page.SearchInteractionPage;
import com.example.steps.MainPageSteps;
import com.example.steps.ProductFiltrationSteps;
import com.example.steps.ProductListSteps;
import com.example.steps.SearchInteractionSteps;
import com.example.utils.Properties;

/**
 * Тест-класс для проверки поиска товара после фильтрации.
 * Этот тест проверяет, что после фильтрации товара по цене и бренду, товар сохраняется,
 * ищется по названию и отображается в результатах поиска.
 *
 * @author Колтыгин Сергей
 */
public class SearchInteractionTest extends BaseTest {

    /**
     * Тест проверяет, что первый отфильтрованный товар можно найти через поиск.
     *
     * @param minPrice      минимальная цена
     * @param maxPrice      максимальная цена
     * @param brand1        первый бренд
     * @param brand2        второй бренд
     * @param expectedCount минимальное количество товаров
     */
    @ParameterizedTest
    @MethodSource("com.example.utils.DataProvider#navigationDataProvider")
    public void verifyProductFoundBySearchAfterFiltering(int minPrice, int maxPrice, String brand1, String brand2, int expectedCount) {
        this.chromeDriver.get(Properties.testsProperties.marketYandexUrl());

        MainPage mainPage = new MainPage(chromeDriver);
        ProductFiltrationPage filtrationPage = new ProductFiltrationPage(chromeDriver);
        ProductListPage productListPage = new ProductListPage(chromeDriver);
        SearchInteractionPage searchPage = new SearchInteractionPage(chromeDriver);

        MainPageSteps mainSteps = new MainPageSteps(mainPage);
        ProductFiltrationSteps filtrationSteps = new ProductFiltrationSteps(filtrationPage, productListPage);
        ProductListSteps productListSteps = new ProductListSteps(productListPage);
        SearchInteractionSteps searchSteps = new SearchInteractionSteps(searchPage, filtrationPage, productListPage);

        mainSteps.clickCatalogButton();
        mainSteps.hoverOnElectronics();
        mainSteps.clickLaptops();

        filtrationSteps.setPriceRange(minPrice, maxPrice);
        filtrationSteps.selectManufacturers(brand1, brand2);

        productListSteps.verifyNumberOfProductsMoreThan(expectedCount);
        productListSteps.verifyPricesInRange(minPrice, maxPrice);
        productListSteps.verifyManufacturersAreOnly(brand1, brand2);
        productListSteps.scrollToTop();

        searchSteps.saveFirstProductTitle();
        searchSteps.searchSavedProduct();
        searchSteps.verifySearchedProductIsInResults();
    }
}

