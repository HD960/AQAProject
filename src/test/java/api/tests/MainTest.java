package api.tests;

import api.data.Book;
import api.data.User;
import api.spec.Specification;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static api.spec.Auth.getTokenForAdmin;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static utils.Const.OWNER_DOUHAN;


@Owner(OWNER_DOUHAN)
public class MainTest extends BaseTest {

    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static String token;

    @BeforeAll
    static void getToken() {
        token = getTokenForAdmin();
    }

    @Test
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
    void getBookIdTest() {

        Book book = given()
                .spec(Specification.requestSpecification(BASE_URL))
                .when()
                .get("/booking/1")
                .then()
                .statusCode(200)
                .log().all().extract().as(Book.class);
        Assertions.assertNotNull(book.getFirstname());
    }

    @Test
    void postBookIdTest() {

        Book updateBook = Book.builder()
                .totalprice(500)
                .build();
        Book book = given()
                .spec(Specification.requestSpecification(BASE_URL))
                .header("Accept", "application/json")
                .cookie("token", token)
                .body(updateBook)
                .when()
                .patch("/booking/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(Book.class);
        Assertions.assertNotNull(book.getFirstname());
        Assertions.assertEquals(500, book.getTotalprice());
    }

    @Test
    void getAuthTest() {

        User user = User.builder()
                .username("admin")
                .password("password123")
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
