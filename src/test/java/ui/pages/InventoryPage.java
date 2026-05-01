package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class InventoryPage implements OpenMenu {


    public static void openCardWithName(String productName){
        $x("//*[text()=\""+ productName +"\"]").click();
    }

    public static void openOptionSorted(){
        $(".product_sort_container").click();
    }

    public static void selectOptionSorted(String nameSorted){
        $x("//select//option[contains(text(), \""+ nameSorted +"\")]").click();
    }

    public static void goToBasket(){
        $x("//a[@class=\"shopping_cart_link\"]").click();
    }

    public static void addSomeProduct(Long count){
        $$x("//button[contains(@class,\"btn_inventory\")]").stream().limit(count).forEach(SelenideElement::click);
    }

}
