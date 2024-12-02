package com.nju.mystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MyStoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyStoreApplication.class, args);
	}

}
