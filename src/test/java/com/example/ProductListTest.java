package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import com.example.page.MainPage;
import com.example.page.ProductFiltrationPage;
import com.example.page.ProductListPage;
import com.example.steps.MainPageSteps;
import com.example.steps.ProductFiltrationSteps;
import com.example.steps.ProductListSteps;
import com.example.utils.Properties;

/**
 * Тест-класс для проверки поиска и фильтрации товаров на Яндекс.Маркете.
 * Этот тест проверяет, что после применения фильтров по цене и бренду,
 * выполняется поиск, и на странице отображаются только соответствующие товары.
 *
 * @author Колтыгин Сергей
 */
public class ProductListTest extends BaseTest {
    @ParameterizedTest
    @MethodSource("com.example.utils.DataProvider#navigationDataProvider")
    public void verifyProductFoundBySearchAfterFiltering(int minPrice, int maxPrice, String brand1, String brand2, int expectedCount) {
        this.chromeDriver.get(Properties.testsProperties.marketYandexUrl());

        MainPage mainPage = new MainPage(chromeDriver);
        ProductFiltrationPage filtrationPage = new ProductFiltrationPage(chromeDriver);
        ProductListPage productListPage = new ProductListPage(chromeDriver);

        MainPageSteps mainSteps = new MainPageSteps(mainPage);
        ProductFiltrationSteps filtrationSteps = new ProductFiltrationSteps(filtrationPage, productListPage);
        ProductListSteps productListSteps = new ProductListSteps(productListPage);

        mainSteps.clickCatalogButton();
        mainSteps.hoverOnElectronics();
        mainSteps.clickLaptops();

        filtrationSteps.setPriceRange(minPrice, maxPrice);
        filtrationSteps.selectManufacturers(brand1, brand2);

        productListSteps.verifyNumberOfProductsMoreThan(expectedCount);
        productListSteps.verifyPricesInRange(minPrice, maxPrice);
        productListSteps.verifyManufacturersAreOnly(brand1, brand2);
        productListSteps.scrollToTop();
    }
}

