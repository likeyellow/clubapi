package org.zerock.clubapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ClubapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubapiApplication.class, args);
	}

}
