package com.andrekreou.iot.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import java.time.Duration;

public class TestContainersConfig {

    static PostgreSQLContainer postgres;

    @BeforeAll
    static void beforeAll() {
        postgres = new PostgreSQLContainer<>("pgvector/pgvector:pg16")
                .withExposedPorts(5432)
                .withReuse(true)
                .withStartupTimeout(Duration.ofMinutes(3))
                .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("postgres")));
        postgres.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }
}
