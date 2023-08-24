package com.api.TopicTraverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TopicTraverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicTraverseApplication.class, args);
	}

}
