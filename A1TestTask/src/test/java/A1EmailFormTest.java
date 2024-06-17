import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.*;
import org.potapenko.A1Page;
import org.potapenko.Driver;

import static org.junit.jupiter.api.Assertions.*;

public class A1EmailFormTest {
    private A1Page a1Page;

    @BeforeEach
    void setupPage() {
        a1Page = new A1Page();
    }

    @ParameterizedTest
    @DisplayName("Valid email")
    @ValueSource(strings = {
            "nikita.potap@yandex.ru",
            "breizulleiddoige-2164@yopmail.com",
            "queuxeveissofou-3120@yopmail.com",
            "yaotlmd735@1secmail.ru",
            "Katieattractive@yahoo.com.sg"
    })
    void validEmailTest1() {
        a1Page.scriptSendEmail("nikita.potap@yandex.ru");

        assertAll("Всплывающее окно 'Вы успешно подписались'",
                () -> a1Page.getToastSuccessSubscribe().isEnabled(),
                () -> a1Page.getToastSuccessSubscribe().isDisplayed(),
                () -> assertEquals(" Вы подписались", a1Page.getTitleSuccessSubscribe().getAttribute("innerHTML")),
                () -> assertEquals("Вы успешно подписались на нашу новостную рассылку.", a1Page.getTextSuccessSubscribe().getAttribute("innerHTML"))
        );
    }

    @ParameterizedTest
    @DisplayName("Invalid email")
    @ValueSource(strings = {
            "nikita.potap.yandex.ru",
            "nikita.potap@yandexru",
            "nikita.potap@yandex.rus",
            "nikita@potap@yandex.ru",
    })
    void invalidEmailTest2(String invalidEmail) {
        a1Page.scriptSendEmail(invalidEmail);

        assertAll("Всплывающее окно ошибки 'Невалидный email'",
                () -> a1Page.getToastErrorSubscribe().isEnabled(),
                () -> a1Page.getToastErrorSubscribe().isDisplayed(),
                () -> assertEquals("Ошибка заполнения", a1Page.getTitleErrorSubscribe().getAttribute("innerHTML")),
                () -> assertEquals("Проверьте указанный email.", a1Page.getTextErrorSubscribe().getAttribute("innerHTML"))
        );
    }


    @AfterEach
    void closePage() {
        Driver.close();
    }
}
