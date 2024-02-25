package Chaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	
	@Test
	void test_getUser(ITestContext context) {
		
		//int id=(Integer) context.getAttribute("user_id");//this should come from create user request.
		int id=(Integer) context.getSuite().getAttribute("user_id");
		String bearerToken="94b03ad2f7c3272296a1ae9cb2cbf301f425604d9425e66fd7ca74f450a1b3c3";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id",id)
		.when()
			
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
		
	}
}
