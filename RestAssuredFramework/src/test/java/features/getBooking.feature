Feature: Validate Booking API

 Scenario: Retrieve an existing booking
    Given a valid booking exists with id 1
    When I send a GET request
    Then the response should contain booking details
    
 Scenario: Verify API response for invalid booking ID
  Given User has an invalid booking ID 414
  When User sends GET request to retrieve booking
  Then API should return status code 404
  And response should show booking not found message