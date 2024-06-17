import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import org.junit.jupiter.api.*;
import org.potapenko.A1Page;
import org.potapenko.Driver;

import static org.junit.jupiter.api.Assertions.*;

public class A1EmailFormTest {
    private WebDriver driver;
    private A1Page a1Page;

    @BeforeEach
    void setupAll() {
        driver = Driver.getInstance();
        a1Page = new A1Page();
    }

    @Test
    @DisplayName("Valid email: nikita.potap@yandex.ru")
    void validEmailTest1() {
        a1Page.clickButtonAcceptAll();
        a1Page.scrollDown();
        a1Page.clickInputEmail();
        a1Page.getInputEmail().sendKeys("nikita.potap@yandex.ru");
        a1Page.clickButtonSendEmail();

        assertAll("Всплывающее окно 'Вы успешно подписались'",
                //() -> a1Page.getToastSuccessSubscribe().isEnabled(),
                //() -> a1Page.getToastSuccessSubscribe().isDisplayed(),
                () -> assertEquals(" Вы подписались", a1Page.getTitleSuccessSubscribe().getAttribute("innerHTML")),
                () -> assertEquals("Вы успешно подписались на нашу новостную рассылку.", a1Page.getTextSuccessSubscribe().getAttribute("innerHTML"))
        );
    }

    @ParameterizedTest
    @DisplayName("Invalid email")
    @ValueSource(strings = {
            "nikita.potap.yandex.ru",
            "nikita.potap@yandexru",
            "nikita.potap@yandex.rus"
    })
    void invalidEmailTest2(String invalidEmail) {
        a1Page.clickButtonAcceptAll();
        a1Page.scrollDown();
        a1Page.clickInputEmail();
        a1Page.getInputEmail().sendKeys(invalidEmail);
        a1Page.clickButtonSendEmail();

        assertAll("Всплывающее окно ошибки 'Невалидный email'",
                () -> a1Page.getToastErrorSubscribe().isEnabled(),
                () -> a1Page.getToastErrorSubscribe().isDisplayed(),
                () -> assertEquals("Ошибка заполнения", a1Page.getTitleErrorSubscribe().getAttribute("innerHTML")),
                () -> assertEquals("Проверьте указанный email.", a1Page.getTextErrorSubscribe().getAttribute("innerHTML"))
        );
    }

    @AfterEach
    void closeDriver() {
        Driver.close();
    }
}
