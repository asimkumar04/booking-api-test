Feature: Validate Booking API

 Scenario: Retrieve an existing booking
 Given a valid booking Id is provided
 When user sends a get request with valid booking id
 Then verify booking details is returned with success code for bookingid 1
 
 
 
 