package org.egualpam.contexts.observable.order.application.ports.`in`

import org.egualpam.contexts.observable.order.application.domain.Order
import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository

class CreateOrder(private val repository: OrderRepository) {
  fun execute(command: CreateOrderCommand) {
    Order.create(command.orderId).let { repository.save(it) }
  }
}

data class CreateOrderCommand(val orderId: String)
