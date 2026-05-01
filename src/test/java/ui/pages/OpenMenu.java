package ui.pages;


import static com.codeborne.selenide.Selenide.$x;

public interface OpenMenu {

    static void clickMenu() {
        $x("//button[@id='react-burger-menu-btn']").click();
    }

}
