package com.example.utils;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * Класс-поставщик тестовых данных для параметризованных тестов.
 * Содержит методы для фильтрации товаров и поиска.
 *
 * @author Колтыгин Сергей
 */
public class DataProvider {
    /**
     * Метод для предоставления тестовых данных для фильтрации товаров.
     * Возвращает поток аргументов для теста.
     * @return поток аргументов: минимальная цена, максимальная цена, производители, ожидаемое количество товаров
     *
     */
    public static Stream<Arguments> productFilterDataProvider() {
        return Stream.of(
                Arguments.of(10000, 20000, "HP", "Lenovo", 12)
        );
    }

    /**
     * Метод для предоставления тестовых данных для поиска товара.
     * @return поток аргументов: минимальная цена, максимальная цена, производители, ожидаемое количество товаров
     */
    public static Stream<Arguments> searchDataProvider() {
        return Stream.of(
                Arguments.of( 10000, 20000,"HP", "Lenovo", 12)
        );
    }

    /**
     * Метод для теста перехода к разделу "Ноутбуки".
     * @return поток аргументов
     */
    public static Stream<Arguments> navigationDataProvider() {
        return Stream.of(
                Arguments.of("Ноутбуки")
        );
    }
}
