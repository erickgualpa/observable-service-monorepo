package org.egualpam.contexts.observable.order.adapters.`in`

import org.egualpam.contexts.observable.order.application.ports.`in`.CreateOrder
import org.egualpam.contexts.observable.order.application.ports.`in`.CreateOrderCommand
import org.slf4j.LoggerFactory.getLogger
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.internalServerError
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/orders")
@RestController
class RestCreateOrder(
  private val createOrder: CreateOrder
) {

  private final val logger = getLogger(this::class.java)

  @PutMapping
  fun createOrder(@RequestBody request: CreateOrderRequest): ResponseEntity<Unit> {
    val command = CreateOrderCommand(request.order.id)

    return try {
      command.let { createOrder.execute(it) }
      noContent().build()
    } catch (e: Exception) {
      logger.error("Unexpected error processing request [$request]:", e)
      internalServerError().build()
    }
  }
}

data class CreateOrderRequest(val order: Order) {
  data class Order(val id: String)
}
