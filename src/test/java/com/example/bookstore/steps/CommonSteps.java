package com.example.bookstore.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.example.bookstore.utils.TestContext;

public class CommonSteps {
    private final TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @Given("the API is running")
    public void apiIsRunning() {
        // just a placeholder check for context
        assertThat(context.getBaseUrl(), notNullValue());
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int status) {
        Response resp = context.getLastResponse();
        resp.then().statusCode(status);
    }
}
