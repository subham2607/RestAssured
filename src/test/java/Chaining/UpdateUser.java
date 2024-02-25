package Chaining;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class UpdateUser {

	@Test
	void updateUser(ITestContext context) {
		
		Faker faker= new Faker();
		
		JSONObject data= new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken ="94b03ad2f7c3272296a1ae9cb2cbf301f425604d9425e66fd7ca74f450a1b3c3";
		
		//int id=(Integer) context.getAttribute("user_id");
		
		int id=(Integer) context.getSuite().getAttribute("user_id");
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			.body(data.toString())

			
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
			
		.then()
			.statusCode(200)
			.log().all();		
	}
}
