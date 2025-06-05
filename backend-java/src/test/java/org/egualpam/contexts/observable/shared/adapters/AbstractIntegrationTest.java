package org.egualpam.contexts.observable.shared.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.ComposeContainer;

import java.io.File;

@ContextConfiguration(initializers = AbstractIntegrationTest.MySQLInitializer.class)
@ActiveProfiles("integration-test")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SharedApplication.class
)
public abstract class AbstractIntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;

    private static final String MYSQL = "mysql";
    private static final int MYSQL_PORT = 3306;

    private static final ComposeContainer containers = new ComposeContainer(new File("compose-lite.yml"))
            .withExposedService(MYSQL, MYSQL_PORT);

    static {
        containers.start();
    }

    static class MySQLInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            var host = containers.getServiceHost(MYSQL, MYSQL_PORT);
            var port = containers.getServicePort(MYSQL, MYSQL_PORT);

            TestPropertyValues.of(
                    "spring.datasource.url=jdbc:mysql://%s:%d/wis".formatted(host, port)
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}