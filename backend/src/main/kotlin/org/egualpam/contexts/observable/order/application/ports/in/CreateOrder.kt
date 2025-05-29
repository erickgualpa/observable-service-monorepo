package org.egualpam.contexts.observable.order.application.ports.`in`

import org.egualpam.contexts.observable.order.application.domain.Order
import org.egualpam.contexts.observable.order.application.ports.out.OrderRepository
import org.egualpam.contexts.observable.shared.application.ports.out.EventBus

class CreateOrder(
  private val repository: OrderRepository,
  private val eventBus: EventBus
) {
  fun execute(command: CreateOrderCommand) {
    val order = Order.create(command.orderId)
    repository.save(order)
    eventBus.publish(order.pullDomainEvents())
  }
}

data class CreateOrderCommand(val orderId: String)
