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

public class UpdateBookingStepDefinitions {
	
private static String token;
private Response putResponse;
Booking booking = new Booking();
	
@Given("a valid booking is provided with id {int}")
public void a_valid_booking_is_provided_with_id(Integer id) {
    // Write code here that turns the phrase above into concrete actions
	token = ApiUtility.getToken();
	
	Map<String, String> headers = new HashMap<>();
	headers.put("Cookie", "token="+token);
	
	Map<String, String> params = new HashMap<>();
	params.put("id", String.valueOf(id));
	
	Response getResponse = ApiMethod.get("/booking/{id}", headers, params, null);

    getResponse.then().statusCode(200);
}

@Given("user has updated booking details")
public void user_has_updated_booking_details() {
    // Write code here that turns the phrase above into concrete actions
	
	booking.setBookingid(1);
	booking.setFirstname("James");
	booking.setLastname("Dean");
	booking.setRoomid(1);
	booking.setDepositpaid(true);
}

@When("I send a PUT request with booking id {int}")
public void i_send_a_put_request_with_booking_id(Integer id) {
    // Write code here that turns the phrase above into concrete actions
	
	Map<String, String> headers = new HashMap<>();
	headers.put("Cookie", "token="+token);
	
	Map<String, String> params = new HashMap<>();
	params.put("id", String.valueOf(id));
	
	putResponse = ApiMethod.put("/booking/{id}", booking, headers, params);
}

@Then("the booking is updated and response code is {int}")
public void the_booking_is_updated_and_response_code_is(Integer successcode) {
    // Write code here that turns the phrase above into concrete actions
	
	assert putResponse.statusCode() == successcode;
	assert putResponse.jsonPath().get("success") == "true";
}



}
