package org.egualpam.contexts.observable.health;

import org.egualpam.contexts.observable.shared.adapters.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;

@AutoConfigureObservability
class ApplicationHealthCheck extends AbstractIntegrationTest {

    @Test
    void application_status_should_be_UP() {
        var expected = """
                {
                    "status": "UP"
                }
                """;
        webTestClient.get()
                .uri("/actuator/health")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expected);
    }

    @Test
    void application_prometheus_metrics_endpoint_should_be_available() {
        webTestClient.get()
                .uri("/actuator/prometheus")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
