package ui.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CheckoutPage implements OpenMenu {

    public static void fillInserts(String firstname, String lastname, String code) {
        $("#first-name").setValue(firstname);
        $("#last-name").setValue(lastname);
        $("#postal-code").setValue(code);
    }

    public static void clickToContinue() {
        $("#continue").click();
    }

    public static void clickToFinish() {
        $x("//button[contains(text(), \"Finish\")]").click();
    }
}
