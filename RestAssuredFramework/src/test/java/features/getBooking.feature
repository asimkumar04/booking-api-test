Feature: Validate Booking API

 Scenario: Retrieve an existing booking
    Given a valid booking exists with id 1
    When I send a GET request
    Then the response should contain booking details