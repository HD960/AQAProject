package ui.pages;

import static com.codeborne.selenide.Selenide.$x;

public class BasketPage implements OpenMenu {

    public static void removeProduct() {
        $x("//button[contains(text(), \"Remove\")]").click();
    }

    public static void goToCheckout() {
        $x("//button[contains(text(), \"Checkout\")]").click();
    }
}
