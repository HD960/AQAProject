package ui.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.OpenMenu;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static ui.pages.LoginPage.openLoginPage;
import static ui.pages.LoginPage.setLoginFormAndClick;
import static ui.pages.SidebarMenuPage.closeSidebarMenu;
import static ui.pages.SidebarMenuPage.logout;
import static utils.Const.*;

@Owner(OWNER_DOUHAN)
public class SidebarMenuPageTest extends BaseTest {

    @BeforeEach
    void init() {
        openLoginPage();
        setLoginFormAndClick(USER_LOGIN, USER_PASSWORD);
        OpenMenu.clickMenu();
    }

    @Test
    void logoutTest() {
        logout();
        $("#login-button").shouldBe(visible);
    }

    @Test
    void closeSidebarTest() {
        closeSidebarMenu();
        $(".bm-menu").shouldBe(hidden);
    }
}
