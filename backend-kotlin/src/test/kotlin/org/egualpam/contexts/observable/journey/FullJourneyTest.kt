package org.egualpam.contexts.observable.journey

import org.egualpam.contexts.observable.shared.adapters.AbstractIntegrationTest
import org.junit.jupiter.api.Test
import org.testcontainers.shaded.com.google.common.net.HttpHeaders.CONTENT_TYPE
import java.util.UUID.randomUUID

class FullJourneyTest : AbstractIntegrationTest() {

  @Test
  fun `full journey`() {
    val orderId = randomUUID().toString()

    val createOrderRequest = """
      {
        "order": {
          "id": "$orderId"
        }
      }
    """

    webTestClient.put()
        .uri("/v1/orders")
        .header(CONTENT_TYPE, "application/json")
        .bodyValue(createOrderRequest)
        .exchange()
        .expectStatus()
        .isNoContent

    webTestClient.get()
        .uri("/v1/orders/{order-id}", orderId)
        .exchange()
        .expectStatus()
        .isOk
  }
}
