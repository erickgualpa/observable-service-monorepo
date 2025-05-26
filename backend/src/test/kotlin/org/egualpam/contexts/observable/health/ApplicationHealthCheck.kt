package org.egualpam.contexts.observable.health

import org.egualpam.contexts.observable.shared.adapters.AbstractIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability

@AutoConfigureObservability
class ApplicationHealthCheck : AbstractIntegrationTest() {

  @Test
  fun `application status should be UP`() {
    webTestClient.get()
        .uri("/actuator/health")
        .exchange()
        .expectStatus()
        .isOk
        .expectBody()
        .json(
            """
              {
                "status": "UP"
              }
            """,
        )
  }

  @Test
  fun `application prometheus metrics endpoint is available`() {
    webTestClient.get()
        .uri("/actuator/prometheus")
        .exchange()
        .expectStatus()
        .isOk
  }
}
