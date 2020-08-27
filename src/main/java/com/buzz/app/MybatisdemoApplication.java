package com.buzz.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class MybatisdemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(MybatisdemoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MybatisdemoApplication.class, args);
		
		logger.debug("start.....");
	}

}
