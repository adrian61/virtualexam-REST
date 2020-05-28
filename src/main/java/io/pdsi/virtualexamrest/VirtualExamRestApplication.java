package io.pdsi.virtualexamrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class VirtualExamRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualExamRestApplication.class, args);
	}

}
