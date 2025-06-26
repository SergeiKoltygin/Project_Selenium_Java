package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

/**
 * Базовый класс для всех автотестов.
 * Отвечает за инициализацию и завершение работы WebDriver.
 *
 * @author Колтыгин Сергей
 */
public class BaseTest {

    /**
     * Экземпляр WebDriver для управления браузером.
     */
    protected WebDriver chromeDriver;

    /**
     * Метод, выполняющийся перед каждым тестом.
     * Открывает браузер и настраивает WebDriver.
     */
    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Метод, выполняющийся после каждого теста.
     * Закрывает браузер.
     */
    @AfterEach
    public void after(){
        chromeDriver.quit();
    }
}
