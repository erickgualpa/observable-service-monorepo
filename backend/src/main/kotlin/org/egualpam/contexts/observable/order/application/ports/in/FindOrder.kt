package org.egualpam.contexts.observable.order.application.ports.`in`

import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository

class FindOrder(private val repository: OrderRepository) {
  fun execute(query: FindOrderQuery): OrderDto {
    val order =
        query.orderId.let { repository.find(it) } ?: throw RuntimeException("Order not found")
    return OrderDto(order.getId().value())
  }
}

data class FindOrderQuery(val orderId: String)
data class OrderDto(val id: String)
