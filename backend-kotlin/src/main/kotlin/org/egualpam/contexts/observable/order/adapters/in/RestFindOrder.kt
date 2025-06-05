package org.egualpam.contexts.observable.order.adapters.`in`

import org.egualpam.contexts.observable.order.application.ports.`in`.FindOrder
import org.egualpam.contexts.observable.order.application.ports.`in`.FindOrderQuery
import org.egualpam.contexts.observable.order.application.ports.`in`.OrderDto
import org.egualpam.contexts.observable.shared.adapters.metrics.ErrorMetrics
import org.slf4j.LoggerFactory.getLogger
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.internalServerError
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/orders")
@RestController
class RestFindOrder(
  private val findOrder: FindOrder,
  private val errorMetrics: ErrorMetrics
) {

  private final val logger = getLogger(this::class.java)

  @GetMapping("/{orderId}")
  fun findOrder(@PathVariable orderId: String): ResponseEntity<OrderResponse> {
    val query = FindOrderQuery(orderId)

    return try {
      val order = query.let { findOrder.execute(it) }
      val response = OrderResponse.from(order)
      ok(response)
    } catch (e: Exception) {
      logger.error("Unexpected error processing order with id [$orderId]", e)
      errorMetrics.error(e)
      internalServerError().build()
    }
  }
}

data class OrderResponse(val order: Order) {
  data class Order(val id: String)

  companion object {
    fun from(dto: OrderDto) = OrderResponse(Order(dto.id))
  }
}
