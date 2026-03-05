package utility;

import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertNotNull;

import io.restassured.response.Response;
import pojo.Login;

public class ApiUtility {
	
	 public static String getToken() {

		 Login login = new Login("admin","password");
		 
		 Map<String, String> headers = new HashMap<>();
		 headers.put("Content-Type", "application/json");
			
		 Response response = ApiMethod.post("/auth/login", login, headers);
			
		 String token = response.jsonPath().getString("token");
		 System.out.println("Generated Token: " + token);

		 assertNotNull(token);

	        

	        return token;
	    }
	 
	 

}
