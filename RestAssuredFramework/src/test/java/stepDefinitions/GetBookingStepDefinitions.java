package stepDefinitions;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.Booking;
import static org.hamcrest.Matchers.equalTo;

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

		Hooks.test.info("GET request sent to Booking API");
		Hooks.test.info("Response: " + getResponse.asString());
	    
	}

	@When("I send a GET request")
	public void i_send_a_get_request() {
	    // Write code here that turns the phrase above into concrete actions
		getResponse.then().statusCode(200);
		Hooks.test.info("Booking details exist: ");
	}

	@Then("the response should contain booking details")
	public void the_response_should_contain_booking_details() {
	    // Write code here that turns the phrase above into concrete actions
		try {
        Booking booking = getResponse.as(Booking.class);
        
        assertEquals(booking.getBookingid(), 1);
        assertEquals(booking.getFirstname(), "James");
        assertEquals(booking.getLastname(), "Dean");
        assertEquals(booking.getRoomid(), 1);
        Hooks.test.pass("Booking details is successfully fetched");
		}catch(AssertionError e) {
			Hooks.test.fail("Booking details fetching failed: " + e.getMessage());
            throw e;  
        }
	}
	
	//When we fetch with booking id that does not exist
	
	private Response getResponse2;
	
	private int bookingId;
	
	@Given("User has an invalid booking ID {int}")
	public void user_has_an_invalid_booking_id(int id) {
	    // Write code here that turns the phrase above into concrete actions
       
		bookingId = id;
		Hooks.test.info("Invalid details are provided");
	}

	@When("User sends GET request to retrieve booking")
	public void user_sends_get_request_to_retrieve_booking() {
	    // Write code here that turns the phrase above into concrete actions
		Map<String, String> headers = new HashMap<>();
		headers.put("Cookie", "token="+token);
		
		Map<String, String> params = new HashMap<>();
		params.put("id", String.valueOf(bookingId));
		
		getResponse2 = ApiMethod.get("/booking/{id}", headers, params, null);
		Hooks.test.info("GET request sent to Booking API");
		Hooks.test.info("Response: " + getResponse.asString());
	}

	@Then("API should return status code {int}")
	public void api_should_return_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		getResponse2.then().assertThat().statusCode(404);
		Hooks.test.info("Booking details does not exist: ");
	}

	@Then("response should show booking not found message")
	public void response_should_show_booking_not_found_message() {
	    // Write code here that turns the phrase above into concrete actions
		try {
		getResponse2.then()
        .body("error", equalTo("Failed to fetch booking: 404"));
		Hooks.test.pass("Booking api returns an error");
		}catch(AssertionError e) {
			Hooks.test.fail("Booking feyched successfully: " + e.getMessage());
            throw e;  
        }
	}


}
