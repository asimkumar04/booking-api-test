package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.BookingDates;

import utility.ApiMethod;

import static org.hamcrest.Matchers.*;

public class createBookingStepDefinition {
	
	
	private Response response;
	Booking booking = new Booking();
	BookingDates bookingdates = new BookingDates();
	
	@Given("I have valid booking details")
	public void i_have_valid_booking_details() {
	    // Write code here that turns the phrase above into concrete actions
		booking.setRoomid(14);
		booking.setFirstname("John");
		booking.setLastname("Don");
		booking.setDepositpaid(true);
		bookingdates.setCheckin("2026-03-10");
		bookingdates.setCheckout("2026-03-10");
		booking.setBookingdates(bookingdates);
		booking.setEmail("john.don@email.com");
		booking.setPhone("12345678901");
		
	}

	@When("I send a POST request to bookking API")
	public void i_send_a_post_request_to_bookking_api() {
	    // Write code here that turns the phrase above into concrete actions
	     
			
		 response = ApiMethod.createBooking(booking);
			   
	  }

	@Then("the response should contain booking id with status code {int}")
	public void the_response_should_contain_booking_id_with_status_code(Integer code) {
	    // Write code here that turns the phrase above into concrete actions
		//System.out.println(response.prettyPrint());
		response.then()
        .statusCode(200)
        .body("bookingid", notNullValue());
	}


}
