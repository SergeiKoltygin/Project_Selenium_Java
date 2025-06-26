package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import com.example.page.MainPage;
import com.example.page.ProductFiltrationPage;
import com.example.page.ProductListPage;
import com.example.steps.MainPageSteps;
import com.example.steps.ProductFiltrationSteps;
import com.example.utils.Properties;

/**
 * Тест-класс для проверки фильтрации товаров по цене и производителям.
 * Этот тест использует параметризацию для различных наборов данных,
 * включая минимальную и максимальную цену, а также бренды для фильтрации.
 *
 * @author Колтыгин Сергей
 */
public class ProductFiltrationTest extends BaseTest {

    /**
     * Тест проверяет, что после применения фильтров отображаются только корректные товары.
     *
     * @param minPrice      минимальная цена
     * @param maxPrice      максимальная цена
     * @param brand1        первый бренд
     * @param brand2        второй бренд
     * @param expectedCount минимальное ожидаемое количество товаров
     */
    @ParameterizedTest
    @MethodSource("com.example.utils.DataProvider#navigationDataProvider")
    public void shouldFilterProductsByPriceAndManufacturers(int minPrice, int maxPrice, String brand1, String brand2, int expectedCount) {
        this.chromeDriver.get(Properties.testsProperties.marketYandexUrl());
        MainPage mainPage = new MainPage(chromeDriver);
        MainPageSteps pageSteps = new MainPageSteps(mainPage);
        ProductFiltrationPage filtrationPage = new ProductFiltrationPage(chromeDriver);
        ProductListPage productListPage = new ProductListPage(chromeDriver);
        ProductFiltrationSteps filtrationSteps = new ProductFiltrationSteps(filtrationPage, productListPage);

        pageSteps.clickCatalogButton();
        pageSteps.hoverOnElectronics();
        pageSteps.clickLaptops();

        filtrationSteps.setPriceRange(minPrice, maxPrice);
        filtrationSteps.selectManufacturers(brand1, brand2);
    }
}
