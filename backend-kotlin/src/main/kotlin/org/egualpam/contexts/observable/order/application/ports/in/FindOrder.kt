package org.egualpam.contexts.observable.order.application.ports.`in`

import org.egualpam.contexts.observable.order.application.domain.OrderNotFound
import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository

class FindOrder(private val repository: OrderRepository) {
  fun execute(query: FindOrderQuery): OrderDto {
    val orderId = query.orderId

    val order = orderId.let { repository.find(it) }
      ?: throw OrderNotFound(orderId)

    return OrderDto(order.getId().value())
  }
}

data class FindOrderQuery(val orderId: String)
data class OrderDto(val id: String)
