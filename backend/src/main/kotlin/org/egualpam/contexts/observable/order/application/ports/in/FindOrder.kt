package org.egualpam.contexts.observable.order.application.ports.`in`

class FindOrder {
  fun execute(query: FindOrderQuery): OrderDto {
    return OrderDto(query.orderId)
  }
}

data class FindOrderQuery(val orderId: String)
data class OrderDto(val id: String)
