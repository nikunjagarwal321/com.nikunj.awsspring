package com.nikunj.awsspring.sqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqsApplication.class, args);
	}

}
