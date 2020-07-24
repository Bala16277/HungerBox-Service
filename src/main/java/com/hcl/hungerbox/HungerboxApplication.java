package com.hcl.hungerbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients
public class HungerboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(HungerboxApplication.class, args);
	}

}
