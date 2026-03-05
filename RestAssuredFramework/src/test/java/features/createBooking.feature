Feature: Create a new booking

Scenario: Create a new booking
Given I have valid booking details
When I send a POST request to bookking API
Then the response should contain booking id with status code 201