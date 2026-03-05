Feature: Update Booking API

 Scenario: Update an existing booking
 Given a valid booking is provided with id 1
 And user has updated booking details
 When I send a PUT request with booking id 1
 Then the booking is updated and response code is 200