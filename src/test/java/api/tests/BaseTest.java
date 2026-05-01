package api.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {


    @BeforeAll
    public static void initAll(){
        RestAssured.filters(new AllureRestAssured());
    }
}
