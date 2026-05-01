package ui.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static ui.pages.InventoryPage.*;
import static ui.pages.LoginPage.openLoginPage;
import static ui.pages.LoginPage.setLoginFormAndClick;
import static utils.Const.*;


@Owner(OWNER_DOUHAN)
public class InventoryPageTest extends BaseTest {
    private static final String BACKPACK = "Sauce Labs Backpack";


    @BeforeEach
    void init() {
        openLoginPage();
        setLoginFormAndClick(USER_LOGIN, USER_PASSWORD);
    }


    @Test
    void openCard() {
        openCardWithName(BACKPACK);
        $x("//div[@class=\"inventory_details_desc large_size\"]").shouldBe(visible);
    }

    @Test
    void addProductInBasket() {
        addSomeProduct(6L);
        String textContent = $x("//span[@class=\"shopping_cart_badge\"]").getAttribute("textContent");
        Assertions.assertEquals("6", textContent);
    }

    @Test
    void sortedListProductZ_A() {
        openOptionSorted();
        selectOptionSorted("Name (Z to A)");
        List<String> textContent = $$(".inventory_item_name").stream()
                .map(x -> x.getAttribute("textContent")).toList();
        boolean sortedZ_A = IntStream.range(0, textContent.size() - 1).allMatch(i -> textContent.get(i).compareTo(textContent.get(i + 1)) >= 0);
        Assertions.assertTrue(sortedZ_A);
    }

    @Test
    void sortedListProductUpPrise() {
        openOptionSorted();
        selectOptionSorted("Price (low to high)");
        List<Double> list = $$(".inventory_item_price").stream()
                .map(x -> x.getAttribute("textContent")).filter(Objects::nonNull)
                .map(x -> Double.parseDouble(x.substring(1))).toList();
        boolean sortedUpPrise = IntStream.range(0, list.size() - 1).allMatch(i -> list.get(i) <= list.get(i + 1));
        Assertions.assertTrue(sortedUpPrise);
    }
}
