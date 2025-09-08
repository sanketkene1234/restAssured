Feature: Books CRUD
  Background:
    Given the API is running
    And a registered user is logged in

  Scenario: Create, read, update and delete a book (happy path)
    Given a book payload
    When I POST /books/ with payload and auth
    Then the response status should be 201
    And store response as "created_book"
