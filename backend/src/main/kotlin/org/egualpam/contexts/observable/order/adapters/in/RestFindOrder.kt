package org.egualpam.contexts.observable.order.adapters.`in`

import org.egualpam.contexts.observable.order.application.ports.`in`.FindOrder
import org.egualpam.contexts.observable.order.application.ports.`in`.FindOrderQuery
import org.egualpam.contexts.observable.order.application.ports.`in`.OrderDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/orders")
@RestController
class RestFindOrder(
  private val findOrder: FindOrder
) {

  @GetMapping("/{orderId}")
  fun findOrder(@PathVariable orderId: String): ResponseEntity<OrderResponse> {
    val order = FindOrderQuery(orderId).let { findOrder.execute(it) }
    val response = OrderResponse.from(order)
    return ResponseEntity.ok(response)
  }
}

data class OrderResponse(val order: Order) {
  data class Order(val id: String)

  companion object {
    fun from(dto: OrderDto) = OrderResponse(Order(dto.id))
  }
}
