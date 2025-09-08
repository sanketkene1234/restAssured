Feature: Health endpoint
  Scenario: API health check
    Given the API is running
    When I call GET /health
    Then the response status should be 200
    And response json should contain "status" with value "ok"
