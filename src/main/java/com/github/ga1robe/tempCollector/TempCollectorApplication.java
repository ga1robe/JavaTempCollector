package com.github.ga1robe.tempCollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.ga1robe.tempCollector")
public class TempCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempCollectorApplication.class, args);
	}

}
