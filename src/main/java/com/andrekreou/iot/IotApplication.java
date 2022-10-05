package com.andrekreou.iot;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IotApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);
	}

	/**
	 * This is required so that we can use the @Timed annotation
	 * on methods that we want to time.
	 */
	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}
}
