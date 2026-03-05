package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.Booking;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import utility.ApiMethod;
import utility.ApiUtility;

public class GetBookingStepDefinitions {
	
	private Response getResponse;
	private static String token;
	
	@Given("a valid booking exists with id {int}")
	public void a_valid_booking_exists_with_id(Integer id) {
	    // Write code here that turns the phrase above into concrete actions
		token = ApiUtility.getToken();
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Cookie", "token="+token);
		
		Map<String, String> params = new HashMap<>();
		params.put("id", String.valueOf(id));
		
		getResponse = ApiMethod.get("/booking/{id}", headers, params, null);

	    
	}

	@When("I send a GET request")
	public void i_send_a_get_request() {
	    // Write code here that turns the phrase above into concrete actions
		getResponse.then().statusCode(200);
	}

	@Then("the response should contain booking details")
	public void the_response_should_contain_booking_details() {
	    // Write code here that turns the phrase above into concrete actions
        Booking booking = getResponse.as(Booking.class);
        
        assertEquals(booking.getBookingid(), 1);
        assertEquals(booking.getFirstname(), "James");
        assertEquals(booking.getLastname(), "Dean");
        assertEquals(booking.getRoomid(), 1);
	}
	

}
