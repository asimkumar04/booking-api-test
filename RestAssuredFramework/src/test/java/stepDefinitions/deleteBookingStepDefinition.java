package stepDefinitions;


import java.util.HashMap;

import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.Booking;
import utility.ApiUtility;
import utility.ApiMethod;


public class deleteBookingStepDefinition {
	
	private static String token;
	Booking booking = new Booking();
	private Response deleteResponse;
	
	@Given("the booking exists with id {int}")
	public void the_booking_exists_with_id(Integer id) {
	    // Write code here that turns the phrase above into concrete actions
		
		token = ApiUtility.getToken();
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Cookie", "token="+token);
		
		Map<String, String> params = new HashMap<>();
		params.put("id", String.valueOf(id));
		
		Response getResponse = ApiMethod.get("/booking/{id}", headers, params, null);

        getResponse.then().statusCode(200);
        Hooks.test.info("Booking details exist: " + booking.getBookingid());
	}

	@When("I send a DELETE request to booking id {int}")
	public void i_send_a_delete_request_to_booking_id(Integer id) {
	    // Write code here that turns the phrase above into concrete actions
		Map<String, String> headers = new HashMap<>();
		headers.put("Cookie", "token="+token);
		
		Map<String, String> params = new HashMap<>();
		params.put("id", String.valueOf(id));
		
		deleteResponse = ApiMethod.delete("/booking/{id}", headers, params);
		
		Hooks.test.info("POST request sent to Booking API");
		Hooks.test.info("Response: " + deleteResponse.asString());
		
	}

	@Then("the ooking shoul be deleted successfully with status code {int}")
	public void the_ooking_shoul_be_deleted_successfully_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		try {
	    assert deleteResponse.statusCode() == 200;
	    Hooks.test.pass("Booking Deleted successfully ");
		}catch(AssertionError e) {
			Hooks.test.fail("Booking deletion failed: " + e.getMessage());
            throw e;  
        }
	}

}
