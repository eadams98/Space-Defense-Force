package com.group.sdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpaceDefenseForceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceDefenseForceApplication.class, args);
	}

}
