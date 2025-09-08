Feature: Authentication
  Background:
    Given the API is running

  Scenario: Signup new user and login
    Given a new user payload
    When I POST /signup with payload
    Then the response status should be 201

    When I POST /login with payload
    Then the response status should be 200
    And response json should contain "access_token"
