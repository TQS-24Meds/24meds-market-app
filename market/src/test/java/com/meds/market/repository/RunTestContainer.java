package com.meds.market.repository;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;


@SuppressWarnings("rawtypes")
public abstract class RunTestContainer {
    private static final DockerImageName MYSQL_IMAGE = DockerImageName.parse("mysql:8.0");
    private static final MySQLContainer container;

    static {
        container = new MySQLContainer<>(MYSQL_IMAGE).withReuse(true);
        container.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}