package ui.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    private static final ElementsCollection LIST_CART_ITEM = $$x("//div//div[@class=\"cart_item\"]");
    private static final SelenideElement BIKE_LIGHT_OBJECT = $x("//div[text()=\"Sauce Labs Bike Light\"]");

    @BeforeEach
    void init() {
        openLoginPage();
        setLoginFormAndClick(USER_LOGIN, USER_PASSWORD);
        addSomeProduct(2L);
        goToBasket();
    }

    @Test
    @DisplayName("Налицие продуктов в корзине, корректное удаление товара из корзины")
    void containsProductFromBasketAndDelFirst() {
        LIST_CART_ITEM.shouldHave(size(2));
        removeProduct();
        LIST_CART_ITEM.shouldHave(size(1));
        BIKE_LIGHT_OBJECT.shouldBe(visible);
    }

}
