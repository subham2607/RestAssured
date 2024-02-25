package SchemaValidations;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//POJO--Serialize --> JSON Object -- Desr---> POJO
public class SerializationDeSerialization {
	
	
	//Pojo --> Json
	//@Test
	void convertPojoToJSON() throws JsonProcessingException {
		Student stupojo = new Student();
		
		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
		String courseArr[]= {"C","C++"};
		stupojo.setCourses(courseArr);
		
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		System.out.println(jsondata);
	}
	
	//Json ---> Pojo
		@Test
		void convertJSONToPojo() throws JsonProcessingException {
			String jsondata = "{\n"
					+ "  \"name\" : \"Scott\",\n"
					+ "  \"location\" : \"France\",\n"
					+ "  \"phone\" : \"123456\",\n"
					+ "  \"courses\" : [ \"C\", \"C++\" ]\n"
					+ "}";
			
			
			//convert json data --> pjo object
			
			ObjectMapper objMapper= new ObjectMapper();
			
			Student stupojo=objMapper.readValue(jsondata,Student.class);//convert json to pojo
			
			System.out.println("Name  "+stupojo.getName());
			stupojo.getLocation();
			stupojo.getPhone();
			System.out.println(stupojo.getCourses()[0]);
			//stupojo.getCourses()[1];
		}
	
}
