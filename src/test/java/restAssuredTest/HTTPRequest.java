package restAssuredTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPRequest {
	int id;
	
	@Test(priority=1)    // when commented will not execute
	public void getUsers() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
		
	}
	@Test(priority=2)
	void createUser() {
		HashMap<String,String> data = new HashMap();
		data.put("name", "subham");
		data.put("job", "triner");
		
		id=given()   //this id is captured from the response
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			
			
			
		//.then()
		//	.statusCode(201)
		//	.log().all();
	}
	
	@Test(priority=3,dependsOnMethods= {"createUser"})
	void updateUser() {
		HashMap<String,String> data = new HashMap();
		data.put("name", "Subham");
		data.put("job", "student");
		
		given()   //this id is captured from the response
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}
}
