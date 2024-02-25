package PathandQueryParameters;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import org.testng.annotations.Test;

public class PathQueryParameters {
	//https://reqres.in/api/users?page=2&id=5
	
	@Test
	void testPathandQueryParameters() {
		given()
			
			.pathParam("mypath", "users")  //path parameters
			.queryParam("page",2)//query parameter
			.queryParam("id", 5)//query parameter
		.when()
			.get("https://reqres.in/api/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
		
	}
}
