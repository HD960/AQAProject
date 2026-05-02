package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class BasketPage implements OpenMenu {

    private static final SelenideElement ELEMENT_REMOVE = $x("//button[contains(text(), \"Remove\")]");
    private static final SelenideElement ELEMENT_CHECKOUT = $x("//button[contains(text(), \"Checkout\")]");

    @Step("Нажать кнопку Remove")
    public static void removeProduct() {
        ELEMENT_REMOVE.click();
    }

    @Step("Нажать кнопку Checkout")
    public static void goToCheckout() {
        ELEMENT_CHECKOUT.click();
    }
}
