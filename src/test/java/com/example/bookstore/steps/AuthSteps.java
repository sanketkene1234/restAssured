package com.example.bookstore.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.example.bookstore.utils.TestContext;

public class AuthSteps {
    private final TestContext context;

    public AuthSteps(TestContext context) {
        this.context = context;
    }

    @Given("a new user payload")
    public void newUserPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", "user" + System.currentTimeMillis());
        payload.put("password", "password123");
        context.setPayload(payload);
    }

    @When("I POST {string} with payload")
    public void postWithPayload(String path) {
        Response resp = given()
            .baseUri(context.getBaseUrl())
            .contentType("application/json")
            .body(context.getPayload())
            .when()
            .post(path);
        context.setLastResponse(resp);
    }
}
