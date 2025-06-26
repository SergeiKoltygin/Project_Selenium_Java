package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import com.example.page.MainPage;
import com.example.steps.MainPageSteps;
import com.example.utils.Properties;

import static com.example.utils.CustomAssertions.assertThat;

/**
 * Тесты для главной страницы Яндекс.Маркета.
 * Проверяет навигацию по каталогу товаров через верхнее меню.
 * Использует параметризованные данные для проверки переходов по разделам.
 *
 * @author Колтыгин Сергей
 */
public class MainPageTest extends BaseTest {

    /**
     * Параметризованный тест, проверяющий корректность перехода к разделу "Ноутбуки" (или другому),
     * указанному в параметрах. Производится клик по кнопке "Каталог", наведение на категорию
     * "Электроника", затем клик на подраздел "Ноутбуки". В конце проверяется, что заголовок страницы
     * содержит ожидаемый текст.
     *
     * @param expectedTitle ожидаемая часть заголовка итоговой страницы
     */
    @ParameterizedTest
    @MethodSource("com.example.utils.DataProvider#navigationDataProvider")
    public void testNavigateToLaptopsSection(String expectedTitle) {
        this.chromeDriver.get(Properties.testsProperties.marketYandexUrl());
        MainPage mainPage = new MainPage(chromeDriver);
        MainPageSteps steps = new MainPageSteps(mainPage);

        steps.clickCatalogButton();
        steps.hoverOnElectronics();
        steps.clickLaptops();
        assertThat(chromeDriver.getTitle().contains(expectedTitle),
                "Заголовок страницы не содержит слово " + expectedTitle + "." + " Факт: " + chromeDriver.getTitle());
    }
}
