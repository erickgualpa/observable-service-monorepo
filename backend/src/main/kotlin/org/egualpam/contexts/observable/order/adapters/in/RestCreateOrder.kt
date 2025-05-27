package org.egualpam.contexts.observable.order.adapters.`in`

import org.egualpam.contexts.observable.order.application.ports.`in`.CreateOrder
import org.egualpam.contexts.observable.order.application.ports.`in`.CreateOrderCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/orders")
@RestController
class RestCreateOrder(
  private val createOrder: CreateOrder
) {

  @PutMapping
  fun createOrder(@RequestBody request: CreateOrderRequest): ResponseEntity<Unit> {
    CreateOrderCommand(request.order.id).let { createOrder.execute(it) }
    return ResponseEntity.noContent().build()
  }
}

data class CreateOrderRequest(val order: Order) {
  data class Order(val id: String)
}
