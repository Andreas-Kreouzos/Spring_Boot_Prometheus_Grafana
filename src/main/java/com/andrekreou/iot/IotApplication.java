package com.andrekreou.iot;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(
		info = @Info(
				title = "Master Thesis Application REST APIs",
				description = "Master Thesis Application REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Andreas",
						email = "andreas.kreouzos@hotmail.com",
						url = "https://www.linkedin.com/in/andreas-kreouzos"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Master Thesis Application Doc",
				url = "https://dione.lib.unipi.gr/xmlui/handle/unipi/14925"
		)
)
@SpringBootApplication
@EnableJpaRepositories
public class IotApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);
	}

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}
}
