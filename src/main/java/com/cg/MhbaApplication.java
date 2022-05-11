package com.cg;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MhbaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MhbaApplication.class, args);

		String logPath = "log4j.properties";
		PropertyConfigurator.configure(logPath);
	}

}
