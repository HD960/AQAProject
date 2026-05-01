package ui.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ui.pages.BasketPage.removeProduct;
import static ui.pages.InventoryPage.addSomeProduct;
import static ui.pages.InventoryPage.goToBasket;
import static ui.pages.LoginPage.openLoginPage;
import static ui.pages.LoginPage.setLoginFormAndClick;
import static utils.Const.*;

@Owner(OWNER_DOUHAN)
public class BasketPageTest extends BaseTest {
    private static final String LIST_CART_ITEM_PATH = "//div//div[@class=\"cart_item\"]";

    @BeforeEach
    void init() {
        openLoginPage();
        setLoginFormAndClick(USER_LOGIN, USER_PASSWORD);
        addSomeProduct(2L);
        goToBasket();
    }

    @Test
    void containsProductFromBasketAndDelFirst() {
        $$x(LIST_CART_ITEM_PATH).shouldHave(size(2));
        removeProduct();
        $$x(LIST_CART_ITEM_PATH).shouldHave(size(1));
        $x("//div[text()=\"Sauce Labs Bike Light\"]").shouldBe(visible);
    }

}
