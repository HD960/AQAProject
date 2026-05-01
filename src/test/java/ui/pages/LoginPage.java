package ui.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class LoginPage {

    private static final String CONTAINS_TEXT_XPATH = "//*[contains(text(), ";

    public static void openLoginPage() {
        Selenide.open("/");
    }

    public static void setLoginFormAndClick(String userName, String password) {
        $("#user-name").setValue(userName);
        $("#password").setValue(password);
        $("#login-button").click();
    }

    public static void checkErrorMessage(String xpathMes) {
        $x(CONTAINS_TEXT_XPATH + "\"" + xpathMes + "\")]").shouldHave(text(xpathMes));
    }

}
