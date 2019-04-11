package com.ravi.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ImageRenderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ImageRenderApplication.class, args);
	}
}
