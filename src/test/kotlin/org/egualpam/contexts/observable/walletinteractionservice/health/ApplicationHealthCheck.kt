package org.egualpam.contexts.observable.walletinteractionservice.health

import org.egualpam.contexts.observable.walletinteractionservice.shared.adapters.AbstractIntegrationTest
import org.junit.jupiter.api.Test

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
}
