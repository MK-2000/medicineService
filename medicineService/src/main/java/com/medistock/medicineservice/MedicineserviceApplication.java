package com.medistock.medicineservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * The Class MedicineServiceApplication.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MedicineserviceApplication {

	/**
	* This method defines a bean for the ModelMapper class.
	* @return an instance of ModelMapper
	*/
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}
	
	/**
	 * The main method is the entry point of the application.
	 * It is responsible for starting the Spring Boot application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MedicineserviceApplication.class, args);
	}

}
 