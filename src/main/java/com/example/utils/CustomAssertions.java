package com.example.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Утилитный класс для пользовательских проверок в тестах.
 *
 * @author Колтыгин Сергей
 */
public class CustomAssertions {

    /**
     * Проверяет, что условие истинно.
     *
     * @param condition логическое условие
     * @param message   сообщение об ошибке в случае провала
     *
     */
    public static void assertThat(boolean condition, String message) {
        assertTrue(condition, "Проверка не прошла " + message);
    }
}

