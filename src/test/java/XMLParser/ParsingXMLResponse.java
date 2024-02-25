package XMLParser;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;



public class ParsingXMLResponse {

	//@Test
	void testXMLResponse() {
		
		//Approach 1
		/*given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"))//we can give an index to get the exact data			
			
			.log().all(); */
		
		//Approach 2
		
		Response res=
		given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		String pageNo=res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		
		
		
	}
	
	//Approach 2 using XmlPath.
	@Test
		void testXMLResponseBody() {
			
			
			Response res=
			given()
			.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
			
			XmlPath xmlobj=new XmlPath(res.asString());//convert the entire response to string format using asString(). when we want to convert a data into String we use toString()
			
			//verify total number of travellers
			List<String> travellers=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
			Assert.assertEquals(travellers.size(),10);
			
			//verify traveller name is present in response
			boolean status=false;
			List<String> travellers_name=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
			for(String travellername:travellers_name) {
				if(travellername.equals("karen")){
					status=true;
					break;
				}
			}
		}
}
