package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.Login;
import utility.ApiMethod;

public class UpdateBookingStepDefinitions {
	
private static String token;
private Response response;
	
	@Given("a valid booking is provided")
	public void valid_authentication_is_provided() {
	    // Code to fetch token
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		
		Login login = new Login("admin","password");
		
		response = ApiMethod.post("/auth/login", login, headers);

        response.then().statusCode(200);
	}

	@When("user updates request with valid details")
	public void user_provides_valid_booking_id() {
	    // Write code here that turns the phrase above into concrete actions
		
        token = response.jsonPath().getString("token");
        System.out.println("Generated Token: " + token);

        assertNotNull(token);

	}

	@Then("verify booking details is returned with success code")
	public void verify_correct_details_is_returned() {
	    // Write code here that turns the phrase above into concrete actions
		int id = 1;
		Booking booking = new Booking();
		booking.setBookingid(1);
		booking.setFirstname("James");
		booking.setLastname("Dean");
		booking.setRoomid(1);
		booking.setDepositpaid(true);
		
		
		Response getResponse = given()
		        .header("Cookie", "token=" + token)
		        .pathParam("id", id)
		        .body(booking)
		    .when()
		        .put("/booking/{id}");

		assert getResponse.statusCode() == 200;

		String success = getResponse.jsonPath().get("success");
		assert success == "true";
        

        
	}

}
