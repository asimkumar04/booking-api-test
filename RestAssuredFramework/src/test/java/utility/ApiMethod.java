package utility;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Booking;

import static io.restassured.RestAssured.given;

import java.util.Map;


public class ApiMethod {
	

	Booking booking = new Booking();
	
	   static {
		   RestAssured.baseURI = ConfigReader.getProperty("base.url");
	   }

	    // Base Request Specification
	    private static RequestSpecification getRequestSpec() {
	        return RestAssured.given()
	                .contentType("application/json");
	    }
	    

		// ==========================
	    // GET METHOD
	    // ==========================
	    public static Response get(String endpoint,
	                               Map<String, String> headers,
	                               Map<String, String> pathParams,
	                               Map<String, String> queryParams) {

	        RequestSpecification request = getRequestSpec();

	        if (headers != null)
	            request.headers(headers);

	        if (pathParams != null)
	            request.pathParams(pathParams);

	        if (queryParams != null)
	            request.queryParams(queryParams);

	        return request.get(endpoint);
	    }

	    // ==========================
	    // POST METHOD
	    // ==========================
	    public static Response post(String endpoint,
	                                Object body,
	                                Map<String, String> headers) {

	        RequestSpecification request = getRequestSpec();

	        if (headers != null)
	            request.headers(headers);

	        if (body != null)
	            request.body(body);

	        return request.post(endpoint);
	    }

	    // ==========================
	    // PUT METHOD
	    // ==========================
	    public static Response put(String endpoint,
	                               Object body,
	                               Map<String, String> headers,
	                               Map<String, String> pathParams) {

	        RequestSpecification request = getRequestSpec();

	        if (headers != null)
	            request.headers(headers);

	        if (pathParams != null)
	            request.pathParams(pathParams);

	        if (body != null)
	            request.body(body);

	        return request.put(endpoint);
	    }

	    // ==========================
	    // DELETE METHOD
	    // ==========================
	    public static Response delete(String endpoint,
	                                  Map<String, String> headers,
	                                  Map<String, String> pathParams) {

	        RequestSpecification request = getRequestSpec();

	        if (headers != null)
	            request.headers(headers);

	        if (pathParams != null)
	            request.pathParams(pathParams);

	        return request.delete(endpoint);
	    }
	    
	    //Create Booking Method
	    
	    public static Response createBooking(Booking booking) {

	        Response response = given()
	                .header("Content-Type", "application/json")
	                .header("Accept", "application/json")
	                .body(booking)
	            .when()
	                .post("/booking");

	        return response;
	    }
	

}
