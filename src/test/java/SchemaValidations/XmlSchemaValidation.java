package SchemaValidations;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XmlSchemaValidation {
	
	@Test
	void xmlSchemavalidtaion() {
		
		given()
			
		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchemaValidation.xsd"));
		
	}
}
