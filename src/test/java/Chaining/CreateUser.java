package Chaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class CreateUser {

	@Test
	void test_createUser(ITestContext context) {
		
		Faker faker= new Faker();
		
		JSONObject data= new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken ="94b03ad2f7c3272296a1ae9cb2cbf301f425604d9425e66fd7ca74f450a1b3c3";
		
		int id = given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id"); // only capture id from the response
		
		System.out.println("Generated id is :"+id);

		//context.setAttribute("user_id", id);//availble at test level
		context.getSuite().setAttribute("user_id", id);//available at suite level
	}
}
