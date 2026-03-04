Feature: Update Booking API

 Scenario: Update an existing booking
 Given a valid booking is provided
 When user updates request with valid details
 Then verify booking details is returned with success code