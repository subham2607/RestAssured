package Authentications;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class authentications {

	
	@Test(priority=1)
	void testBasicAuthentication() {
		
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	@Test(priority=2)
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	@Test(priority=3)
	void testPreemptiveAuthentication() {
		//combination of basic and digest
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	void testBearerTokenAuthentication() {
		
		String BearerToken="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";
		
		given()
			.headers("Authorization","Bearer "+BearerToken)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test
	void tetsOAuth1Authetication() {
		
		given()
			.auth().oauth("consumerKey","consumerSecret","accessToken","tokenSecret")//Oauth 1.0 Authentication
		.when().get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test
		void tetsOAuth2Authetication() {
			
			given()
				.auth().oauth2("")//Oauth 2//.0 Authentication
			.when().get("url")
			.then()
				.statusCode(200)
				.log().all();
		}
		
		@Test
		void testAPIKeyInfo() {
			
			given()
				.queryParam("appid", "")//appid is key
			.when()
				.get("")
			.then()
				.statusCode(200)
				.log().all();
		}
	
}
