package ui.tests;

import com.codeborne.selenide.junit5.SoftAssertsExtension;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ui.pages.LoginPage.*;
import static utils.Const.*;

@Owner(OWNER_DOUHAN)
@ExtendWith({SoftAssertsExtension.class})
public class LoginPageTest extends BaseTest {
    private static final String EXPECTED_BLOCKED_MES = "Sorry, this user has been locked out.";
    private static final String EXPECTED_NOT_USER_MES = "Username and password do not match any user in this service";
    private static final String EXPECTED_PASS_MES = "Password is required";

    @BeforeEach
    void init() {
        openLoginPage();
    }


    @Test
    void successLogin() {
        setLoginFormAndClick(USER_LOGIN, USER_PASSWORD);
        $("#react-burger-menu-btn").shouldBe(visible);
    }

    @Test
    void blockedLogin() {
        setLoginFormAndClick(BLOCKED_USER_LOGIN, USER_PASSWORD);
        checkErrorMessage(EXPECTED_BLOCKED_MES);
    }

    @DisplayName("Некорректный логин")
    @Test
    void incorrectedLogin() {
        setLoginFormAndClick(INCORRECT_USER_LOGIN, USER_PASSWORD);
        checkErrorMessage(EXPECTED_NOT_USER_MES);
    }

    @Test
    void notPasswordFromLogin() {
        setLoginFormAndClick(USER_LOGIN, "");
        $x("//button[@class=\"error-button\"]").shouldBe(visible);
        checkErrorMessage(EXPECTED_PASS_MES);
    }
}
