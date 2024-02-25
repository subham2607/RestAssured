package ParsingResponseBody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseData {
 
	//@Test(priority=1)
	void testJsonResponse() {
		
		//Approach1 :- assertion in Then 
		
		/*given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[0].title",equalTo("Moby Dick"));*/
		
		//Approach2 :- get response in a variable and then do the assertions
		Response res=given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(),200);   //validation 1
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
		String bookname =res.jsonPath().get("book[0].title").toString();
		Assert.assertEquals(bookname, "Moby Dick");
		
	}
	
	@Test(priority=2)
	void testJsonResponseBodyData() {
		
		
		
		//Approach2 :- get response in a variable and then do the assertions
		Response res=given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		/*Assert.assertEquals(res.getStatusCode(),200);   //validation 1
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
		String bookname =res.jsonPath().get("book[0].title").toString();
		Assert.assertEquals(bookname, "Moby Dick");*/
		
		//JSONObject class--> to read Json file, to parsh the entire JSOn response
		
		JSONObject jo= new JSONObject(res.asString()); // converting response to JSOn Object type
		
		/*for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(booktitle);
		}*/
		boolean status =false;
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(booktitle.equals("Moby Dick")) {
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
		//validate total price of books
		
		double totalprice=0;
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String price =jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			totalprice = totalprice+Double.parseDouble(price);
			
		}
		System.out.println(totalprice);
		
	}
}
