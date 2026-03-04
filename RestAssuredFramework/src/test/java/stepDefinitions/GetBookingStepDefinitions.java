package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.Login;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import utility.ApiMethod;

public class GetBookingStepDefinitions {
	
	private Response response;
	private static String token;
	
	@Given("a valid booking Id is provided")
	public void valid_authentication_is_provided() {
	    // Code to fetch token
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		
		Login login = new Login("admin","password");
		
		response = ApiMethod.post("/auth/login", login, headers);

        response.then().statusCode(200);
	}

	@When("user sends a get request with valid booking id")
	public void user_provides_valid_booking_id() {
	    // Write code here that turns the phrase above into concrete actions
		
        token = response.jsonPath().getString("token");
        System.out.println("Generated Token: " + token);

        assertNotNull(token);

	}

	@Then("verify booking details is returned with success code for bookingid {int}")
	public void verify_correct_details_is_returned(int bookingId) {
	    // Write code here that turns the phrase above into concrete actions
		int id = bookingId;
		
		Response getResponse = given()
		        .header("Cookie", "token=" + token)
		        .pathParam("id", id)
		    .when()
		        .get("/booking/{id}");

        getResponse.then().statusCode(200);
        System.out.println(getResponse.prettyPrint());
        
        Booking booking = getResponse.as(Booking.class);
        
        assertEquals(booking.getBookingid(), 1);
        assertEquals(booking.getFirstname(), "James");
        assertEquals(booking.getLastname(), "Dean");
        assertEquals(booking.getRoomid(), 1);
        

        
	}

}
