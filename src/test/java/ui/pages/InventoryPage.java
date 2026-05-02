package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class InventoryPage implements OpenMenu {

    @Step("Открытие карточки товара")
    public static void openCardWithName(String productName){
        $x("//*[text()=\""+ productName +"\"]").click();
    }

    @Step("Открытие формы сортировки продуктов")
    public static void openOptionSorted(){
        $(".product_sort_container").click();
    }

    @Step("Выбор типа сорировки продутов на странице")
    public static void selectOptionSorted(String nameSorted){
        $x("//select//option[contains(text(), \""+ nameSorted +"\")]").click();
    }

    @Step("Переход на страницу корзины")
    public static void goToBasket(){
        $x("//a[@class=\"shopping_cart_link\"]").click();
    }

    @Step("Добавление продуктов в корзину")
    public static void addSomeProduct(Long count){
        $$x("//button[contains(@class,\"btn_inventory\")]").stream().limit(count).forEach(SelenideElement::click);
    }

}
