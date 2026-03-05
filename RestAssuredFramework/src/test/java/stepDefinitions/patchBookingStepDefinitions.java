package stepDefinitions;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
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
	        Hooks.test.info("Booking details exist in system");
		    
	}

	@Given("I have updated booking details")
	public void i_have_updated_booking_details() {
	    // Write code here that turns the phrase above into concrete actions
		
		booking.setBookingid(1);
		booking.setFirstname("James1");
		booking.setLastname("Dean1");
		Hooks.test.info("Booking ID verified in system");
		Hooks.test.info("Token generated: " + token);
	}

	@When("I send a PUT request to booking id {int}")
	public void i_send_a_put_request_to(Integer id) {
	    // Write code here that turns the phrase above into concrete actions

		Map<String, String> headers = new HashMap<>();
		headers.put("Cookie", "token="+token);
		
		Map<String, String> params = new HashMap<>();
		params.put("id", String.valueOf(id));
		
		putResponse = ApiMethod.put("/booking/{id}", booking, headers, params);
		Hooks.test.info("PUT request sent to Booking API");
	    Hooks.test.info("Response: " + putResponse.asString());
	}

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(int successcode) {
	    // Write code here that turns the phrase above into concrete actions
		//System.out.print(putResponse.prettyPrint());
		putResponse.then().statusCode(successcode);
		Hooks.test.info("Booking details is updated in system ");

	}

	@Then("the response should indicate success")
	public void the_response_should_indicate_success() {
	    // Write code here that turns the phrase above into concrete actions
		try {
		putResponse.then().body("success", equalTo("true"));
		Hooks.test.pass("Booking details is successfully updated");
		}catch(AssertionError e) {
            Hooks.test.fail("Booking updation failed: " + e.getMessage());
            throw e;  
        }
		
	}


}
