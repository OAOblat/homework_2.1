package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class OrderTest {
    @Test
    void shouldHappyPath() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79210000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldFailedSendingWithLatinLettersInNameField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Ivanov Ivan");
        form.$("[data-test-id=phone] input").setValue("+79210000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailedSendingWithNumbersInNameField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов 777");
        form.$("[data-test-id=phone] input").setValue("+79210000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailedSendingWithUnderliningInNameField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов_Иван");
        form.$("[data-test-id=phone] input").setValue("+79210000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailedSendingWithSpecialCharactersInNameField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов #@$%");
        form.$("[data-test-id=phone] input").setValue("+79210000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailedSendingWithEmptyFieldInNameField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79210000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldFailedSendingWithEmptyFieldInPhoneField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=phone] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldFailedSendingWithoutPlusInPhoneField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("79210000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFailedSendingWith10DigitsInPhoneField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+7921000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFailedSendingWith12DigitsInPhoneField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+792100000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFailedSendingWithLettersInPhoneField() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("Phone");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFailedSendingWithoutCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79210000000");
        form.$(".button_theme_alfa-on-white").click();
        form.$(".input_invalid").shouldHave(exactText("соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
