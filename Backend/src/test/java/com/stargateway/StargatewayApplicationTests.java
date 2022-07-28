package com.stargateway;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stargateway.entities.ContactUs;
import com.stargateway.services.ContactUsServices;
import com.stargateway.services.UserServices;

@SpringBootTest
class StargatewayApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Autowired
	private ContactUsServices contactUsServices;
	

	
	@Transactional
	@Test
	void findByUserId() {
		ContactUs contactUs = contactUsServices.findContactUs();

		System.out.println(contactUs);
	}
	

}
