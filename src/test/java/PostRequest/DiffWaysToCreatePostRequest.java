package PostRequest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DiffWaysToCreatePostRequest{

	//1.post Request body using HashMap
	//@Test(priority=1)
	void testPostusingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "Sonu");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[]= {"C","C++"};
		data.put("courses", courseArr);
		
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Sonu"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();	
	}
	
	@Test(priority=2)
	void testDelete() {
		given()
		
		.when()
			.delete("http://localhost:3000/students/3")
		.then()
			.statusCode(200);
			
		
	}
	//2. Post request body using org.json library
	
	//@Test(priority=1)
	void testPostusingJsonLibrary() {
		
		JSONObject data = new JSONObject();
		data.put("name", "Subham");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String coursesArr[] = {"C","C++"};
		data.put("courses", coursesArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())// here the data should be in string formate
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Subham"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();	
	}
	
	//3.using POJO
		//@Test(priority=1)
		void testPostusingPOJO() {
			
			pojo_postRequest data = new pojo_postRequest();
			data.setName("Subham");
			data.setLocation("France");
			data.setPhone("123456");
			
			String coursesArr[]= {"C","C++"};
			
			data.setCourses(coursesArr);
			
			given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("Subham"))
				.body("location", equalTo("France"))
				.body("phone", equalTo("123456"))
				.body("courses[0]",equalTo("C"))
				.body("courses[1]",equalTo("C++"))
				.header("Content-Type","application/json; charset=utf-8")
				.log().all();	
		}
		//4.using ExternalFIle
		@Test(priority=1)
		void testPostusingExternalJsonFIle() throws FileNotFoundException {
			
			File f = new File("/Users/subhamkumar/eclipse-workspace/RestAssuredAPITesting_BDD_Project/Body.json");
			FileReader fr = new FileReader(f);
			JSONTokener jt = new JSONTokener(fr);
			JSONObject data = new JSONObject(jt);
			
			given()
				.contentType("application/json")
				.body(data.toString())
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("John"))
				.body("location", equalTo("France"))
				.body("phone", equalTo("123456"))
				.body("courses[0]",equalTo("C"))
				.body("courses[1]",equalTo("C++"))
				.header("Content-Type","application/json; charset=utf-8")
				.log().all();	
		}
	
	
}