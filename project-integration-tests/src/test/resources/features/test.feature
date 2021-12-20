Feature: Test Feature

  @test
  Scenario: I have a scenario of test
    Given I have a "valid" authentication token
    And  I am on country "JP"
    And the following integrated services will have the corresponding responses:
      | serviceName   | action | responseStatus | referenceResponseName | queryParameters |
      | endpoint_test | GET    | 200            | default               | param=test      |
    And I have a request with the following generic data:
      | param1 | param2 | param3 |
      | 1234   | test   | param  |
    When I make a "GET" request to "/v1/test?param=test"
    Then I should get a 200 response code
    And the following service should receive the respective headers:
      | serviceName   | action | key          | value            |
      | endpoint_test | GET    | Content-Type | application/json |
    And I should see the following data in the response body
      | param1 | testValue |