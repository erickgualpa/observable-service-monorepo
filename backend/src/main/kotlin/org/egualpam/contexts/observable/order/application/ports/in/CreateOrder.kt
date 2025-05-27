package org.egualpam.contexts.observable.order.application.ports.`in`

class CreateOrder {
  fun execute(command: CreateOrderCommand) {}
}

data class CreateOrderCommand(val orderId: String)
