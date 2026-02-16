package com.bhavindesai.api.tests;

import com.bhavindesai.api.base.BaseApiTest;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthTests extends BaseApiTest {

    @Test
    public void login_shouldReturnToken_forValidCredentials() {
        String payload = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}";

        String token =
                given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post("/api/login")
                .then()
                    .statusCode(200)
                    .extract()
                    .path("token");

        Assert.assertNotNull(token, "Token should not be null");
    }

    @Test
    public void login_shouldFail_forMissingPassword() {
        String payload = "{\"email\":\"eve.holt@reqres.in\"}";

        String error =
                given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post("/api/login")
                .then()
                    .statusCode(400)
                    .extract()
                    .path("error");

        Assert.assertTrue(error != null && !error.isBlank(), "Expected error message");
    }
}
