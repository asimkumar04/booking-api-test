package stepDefinitions;


import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.Booking;
import utility.ApiMethod;
import utility.ApiUtility;


public class patchBookingStepDefinitions {
	
	
	private static String token;
	Booking booking = new Booking();
	private Response putResponse;
	
	@Given("a booking exists with id {int}")
	public void a_booking_exists_with_id(Integer id) {
	    // Write code here that turns the phrase above into concrete actions
	        
			token = ApiUtility.getToken();
			
			Map<String, String> headers = new HashMap<>();
			headers.put("Cookie", "token="+token);
			
			Map<String, String> params = new HashMap<>();
			params.put("id", String.valueOf(id));
			
			Response getResponse = ApiMethod.get("/booking/{id}", headers, params, null);

	        getResponse.then().statusCode(200);
	}

	@Given("I have updated booking details")
	public void i_have_updated_booking_details() {
	    // Write code here that turns the phrase above into concrete actions
		
		booking.setBookingid(1);
		booking.setFirstname("James1");
		booking.setLastname("Dean1");
	}

	@When("I send a PUT request to booking id {int}")
	public void i_send_a_put_request_to(Integer id) {
	    // Write code here that turns the phrase above into concrete actions

		Map<String, String> headers = new HashMap<>();
		headers.put("Cookie", "token="+token);
		
		Map<String, String> params = new HashMap<>();
		params.put("id", String.valueOf(id));
		
		putResponse = ApiMethod.put("/booking/{id}", booking, headers, params);
	}

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(Integer successcode) {
	    // Write code here that turns the phrase above into concrete actions
		assert putResponse.statusCode() == successcode;

	}

	@Then("the response should indicate success")
	public void the_response_should_indicate_success() {
	    // Write code here that turns the phrase above into concrete actions
		assert putResponse.jsonPath().get("success") == "true";
		
	}


}
