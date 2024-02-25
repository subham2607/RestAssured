package XMLParser;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadDownload {

	@Test
	void singleFIleUpload() {
		
		File myfile = new File("");
		given()
			.multiPart("file",myfile)//mandatory
			.contentType("multipart/form-data")//mandatory
		.when()
			.post("upload path name")
		.then()
			.statusCode(200)
			.body("fileName",equalTo(""))
			.log().all();
	}
	
	//upload multiple files
	@Test
	void multiFIleUpload() {
		
		File myfile1 = new File("");
		File myfile2 = new File("");
		
		//File filearr[]={myfile1,myfile2}// wont work for all the type of api.
		
		given()
			.multiPart("file",myfile1)//mandatory
			.multiPart("file",myfile2)
			
			//.multiPart("file",filearr);
			.contentType("multipart/form-data")//mandatory
		.when()
			.post("upload path name")
		.then()
			.statusCode(200)
			.body("[0].fileName",equalTo(""))
			.body("[1].fileName",equalTo(""))
			.log().all();
	}
	@Test
	public void fileDownload1(){
		
		given()
		.when()
			.get("url where it is downloaded")
		.then()
			.statusCode(200)
			.log().body();
	}
	
	
}
