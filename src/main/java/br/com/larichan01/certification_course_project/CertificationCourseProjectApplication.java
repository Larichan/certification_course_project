package br.com.larichan01.certification_course_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CertificationCourseProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertificationCourseProjectApplication.class, args);
	}

}
