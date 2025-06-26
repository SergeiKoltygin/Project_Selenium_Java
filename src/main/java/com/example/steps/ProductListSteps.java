package com.example.steps;

import io.qameta.allure.Step;
import com.example.page.ProductListPage;
import com.example.utils.CustomAssertions;

import java.util.List;

/**
 * Класс шагов для проверки отображаемых товаров на странице после фильтрации.
 * Содержит методы для валидации количества товаров, диапазона цен и соответствия брендам.
 *
 * @author Колтыгин Сергей
 */
public class ProductListSteps {

    /**
     * Страница со списком товаров.
     */
    private ProductListPage productListPage;

    /**
     * Конструктор класса шагов для списка товаров.
     *
     * @param productListPage объект страницы списка товаров
     */
    public ProductListSteps(ProductListPage productListPage) {
        this.productListPage = productListPage;
    }

    /**
     * Проверяет, что количество отображаемых товаров больше заданного значения.
     *
     * @param expectedCount ожидаемое минимальное количество товаров
     */
    @Step("Проверить, что количество товаров на странице больше {expectedCount}")
    public void verifyNumberOfProductsMoreThan(int expectedCount) {
        int count = productListPage.getNumberOfProductsOnPage();
        CustomAssertions.assertThat(count > expectedCount, "Ожидалось больше " + expectedCount + " товаров на странице, но было " + count);
    }

    /**
     * Проверяет, что все отображаемые товары имеют цену в заданном диапазоне.
     *
     * @param min минимально допустимая цена
     * @param max максимально допустимая цена
     */
    @Step("Проверить, что все цены в диапазоне от {min} до {max}")
    public void verifyPricesInRange(int min, int max) {
        productListPage.scrollToLoadAllProducts();
        List<Long> prices = productListPage.getAllPricesOnPage();
        for (Long price : prices) {
            CustomAssertions.assertThat(price >= min && price <= max, "Цена " + price + " не входит в диапазон от " + min + " до " + max);
        }
    }

    /**
     * Проверяет, что все отображаемые товары принадлежат только указанным брендам.
     *
     * @param allowedBrands список допустимых брендов
     */
    @Step("Проверить, что все товары относятся к производителям {allowedBrands}")
    public void verifyManufacturersAreOnly(String... allowedBrands) {
        productListPage.scrollToLoadAllProducts();
        List<String> titles = productListPage.getAllTitlesOnPage();
        for (String title : titles) {
            boolean matches = false;
            for (String brand : allowedBrands) {
                if (title.contains(brand.toLowerCase())) {
                    matches = true;
                    break;
                }
            }
            CustomAssertions.assertThat(matches, "Товар '" + title + "' не принадлежит брендам: " + String.join(", ", allowedBrands));
        }
    }

    /**
     * Скроллит страницу вверх к началу списка товаров.
     */
    @Step("Возвращаемся в начало списка товаров")
    public void scrollToTop() {
        productListPage.scrollToTop();
    }
}

