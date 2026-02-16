package com.bhavindesai.api.tests;

import com.bhavindesai.api.base.BaseApiTest;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserApiTests extends BaseApiTest {

    @Test
    public void getUsers_shouldReturnPage2_andMatchSchema() {
        given()
        .when()
            .get("/api/users?page=2")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/getUsersPage2.json"));
    }

    @Test
    public void createUser_shouldReturn201_andEchoNameJob() {
        String payload = "{\"name\":\"Bhavin\",\"job\":\"QA Automation Engineer\"}";

        String name =
            given()
                .contentType(ContentType.JSON)
                .body(payload)
            .when()
                .post("/api/users")
            .then()
                .statusCode(201)
                .extract()
                .path("name");

        Assert.assertEquals(name, "Bhavin");
    }
}
