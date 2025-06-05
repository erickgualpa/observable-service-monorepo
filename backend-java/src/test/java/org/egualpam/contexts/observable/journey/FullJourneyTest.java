package org.egualpam.contexts.observable.journey;

import org.egualpam.contexts.observable.shared.adapters.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

class FullJourneyTest extends AbstractIntegrationTest {

    @Test
    void full_journey() {
        var orderId = randomUUID().toString();

        var createOrderRequest = """
                {
                    "order": {
                      "id": "%s"
                    }
                }
                """.formatted(orderId);

        webTestClient.put()
                .uri("/v1/orders")
                .header(CONTENT_TYPE, "application/json")
                .bodyValue(createOrderRequest)
                .exchange()
                .expectStatus()
                .isNoContent();

        webTestClient.get()
                .uri("/v1/orders/{order-id}", orderId)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
