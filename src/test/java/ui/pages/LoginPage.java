package ui.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class LoginPage {

    public static void openLoginPage() {
        Selenide.open("/");
    }

    @Step("Заполнение формы авторизации и нажатие кнопки входа")
    public static void setLoginFormAndClick(String userName, String password) {
        $("#user-name").setValue(userName);
        $("#password").setValue(password);
        $("#login-button").click();
    }

    @Step("Проверка на сообщение об ошибке авторицации")
    public static void checkErrorMessage(String xpathMes) {
        $x("//*[contains(text(), \"" + xpathMes + "\")]").shouldHave(text(xpathMes));
    }

}
