package PathandQueryParameters;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TestHeaders {
	
	//@Test(priority=1)
	void testHeaders() {
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()//optional
			.header("Content-Encoding", "gzip");
	}
	
	@Test(priority=2)
	void getHeaders() {
		
		
		Response res =given()
		.when()
			.get("https://www.google.com/");
		
		//get single header info
		//String headervalue = res.getHeader("Content-Type");
		//System.out.println("the value of Content-type header value" +headervalue);
		
		//get all headers info
		Headers myheaders = res.getHeaders();
		
		for(Header hd :myheaders) {
			System.out.println(hd.getName()+"   "+hd.getValue());
			
		}
	}
}
