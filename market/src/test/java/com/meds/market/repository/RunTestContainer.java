package com.meds.market.repository;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class RunTestContainer {
  
  //@Container
  static final MySQLContainer<?> container =
         new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
          .withDatabaseName("24Meds")
          .withUsername("user")
          .withPassword("user")
          .withReuse(true);
  static {
      container.start();
      // make sure that containers will be stop in fast way (Ryuk can be slow)
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        log.info("DockerContainers stop");
        container.stop();
    }));
  }
    
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}