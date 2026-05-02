package ui.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static ui.pages.BasketPage.goToCheckout;
import static ui.pages.CheckoutPage.*;
import static ui.pages.InventoryPage.addSomeProduct;
import static ui.pages.InventoryPage.goToBasket;
import static ui.pages.LoginPage.openLoginPage;
import static ui.pages.LoginPage.setLoginFormAndClick;
import static utils.Const.*;

@Owner(OWNER_DOUHAN)
public class CheckoutPageTest extends BaseTest {
    private static final String PAYMENT_INFORMATION_PATH = "//div[text()=\"Payment Information:\"]";

    @BeforeEach
    void init() {
        openLoginPage();
        setLoginFormAndClick(USER_LOGIN, USER_PASSWORD);
        addSomeProduct(2L);
        goToBasket();
        goToCheckout();
    }

    @Test
    @DisplayName("Корректный ввод данных для заказа")
    void successSetDataFromInput() {
        fillInserts("Name", "Last", "123456");
        clickToContinue();
        $x(PAYMENT_INFORMATION_PATH).shouldBe(visible);
        clickToFinish();
        $x("//h2[text()=\"Thank you for your order!\"]").shouldBe(visible);
    }

}
