package org.potapenko;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Driver {
    private static WebDriver instance;

    public static WebDriver getInstance() {
        if (instance == null) {
            WebDriverManager.chromedriver().setup();
            instance = new ChromeDriver();
            instance.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            instance.manage().window().maximize();
            instance.get("https://www.a1.by/ru/");
        }
        return instance;
    }

    public static void close() {
        instance.quit();
        instance = null;
    }
}
