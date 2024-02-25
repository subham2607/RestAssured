package Authentications;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	
	Faker faker = new Faker();
	
	String fullname = faker.name().fullName();
	
	
	
}
