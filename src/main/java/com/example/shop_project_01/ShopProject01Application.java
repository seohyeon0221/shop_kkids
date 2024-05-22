package com.example.shop_project_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShopProject01Application {

	public static void main(String[] args) {
		SpringApplication.run(ShopProject01Application.class, args);
	}

}
