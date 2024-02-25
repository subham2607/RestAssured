package Chaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class DeleteUser {
	
	@Test
	void test_DeleteUser(ITestContext context) {
		
		String bearerToken ="94b03ad2f7c3272296a1ae9cb2cbf301f425604d9425e66fd7ca74f450a1b3c3";
		
		//int id=(Integer) context.getAttribute("user_id");//this should come from the create user.
		int id=(Integer) context.getSuite().getAttribute("user_id");
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id",id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();
		
	}

}
