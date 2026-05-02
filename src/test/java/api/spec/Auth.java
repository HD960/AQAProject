package api.spec;

import api.data.User;
import io.qameta.allure.Step;
import utils.ConfigLoader;

import static api.tests.MainTest.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class Auth {

    private static final User user = User.builder()
            .username(ConfigLoader.get().getApi().getConfiguration().getUsername())
            .password(ConfigLoader.get().getApi().getConfiguration().getPassword())
            .build();


    @Step("Получение токена авторизации")
    public static String getTokenForAdmin() {
        return given()
                .spec(Specification.requestSpecification(BASE_URL))
                .basePath("/auth")
                .body(user)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .jsonPath()
                .getString("token");

    }
}
