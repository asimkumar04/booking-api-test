Feature: Delete Booking

 Scenario: Delete a booking
    Given the booking exists with id 5
    When I send a DELETE request to booking id 5
    Then the ooking shoul be deleted successfully with status code 200
    