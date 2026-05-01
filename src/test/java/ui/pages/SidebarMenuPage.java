package ui.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SidebarMenuPage {

    public static void logout() {
        $x("//*[text()='Logout']").click();
    }

    public static void closeSidebarMenu() {
        $("#react-burger-cross-btn").click();
    }
}
