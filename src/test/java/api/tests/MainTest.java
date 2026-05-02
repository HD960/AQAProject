package api.tests;

import api.data.Booking;
import api.data.User;
import api.spec.Specification;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfigLoader;

import static api.spec.Auth.getTokenForAdmin;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static utils.Const.OWNER_DOUHAN;


@Owner(OWNER_DOUHAN)
public class MainTest extends BaseTest {

    public static final String BASE_URL = ConfigLoader.get().getApi().getConfiguration().getBaseUrl();
    private static String token;

    @BeforeAll
    static void getToken() {
        token = getTokenForAdmin();
    }

    @Test
    @DisplayName("Пинг тест апи")
    void pingTest() {

        given()
                .spec(Specification.requestSpecification(BASE_URL))
                .basePath("/ping")
                .when()
                .get()
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    @DisplayName("Получение всех id бронирований")
    void getBookingIdsTest() {

        given()
                .spec(Specification.requestSpecification(BASE_URL))
                .basePath("/booking")
                .when()
                .get()
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    @DisplayName("Получение данных по id = 1")
    void getBookIdTest() {

        Booking booking = given()
                .spec(Specification.requestSpecification(BASE_URL))
                .when()
                .get("/booking/1")
                .then()
                .statusCode(200)
                .log().all().extract().as(Booking.class);
        Assertions.assertNotNull(booking.getFirstname());
    }

    @Test
    @DisplayName("Обновление данных по бронированию")
    void patchBookIdTest() {

        Booking updateBooking = Booking.builder()
                .totalprice(500)
                .build();
        Booking booking = given()
                .spec(Specification.requestSpecification(BASE_URL))
                .header("Accept", "application/json")
                .cookie("token", token)
                .body(updateBooking)
                .when()
                .patch("/booking/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(Booking.class);
        Assertions.assertNotNull(booking.getFirstname());
        Assertions.assertEquals(500, booking.getTotalprice());
    }

    @Test
    @DisplayName("Проверка авторизации пользователя")
    @Severity(BLOCKER)
    void getAuthTest() {

        User user = User.builder()
                .username(ConfigLoader.get().getApi().getConfiguration().getUsername())
                .password(ConfigLoader.get().getApi().getConfiguration().getPassword())
                .build();

        given()
                .spec(Specification.requestSpecification(BASE_URL))
                .basePath("/auth")
                .body(user)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .body("token", notNullValue());

    }
}
