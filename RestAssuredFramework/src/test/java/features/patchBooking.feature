Feature: Update a booking partially

 Scenario: Update an existing booking
    Given a booking exists with id 1
    And I have updated booking details
    When I send a PUT request to booking id 1
    Then the response status code should be 200
    And the response should indicate success