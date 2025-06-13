package org.egualpam.contexts.observable.order.application.domain

import org.egualpam.contexts.observable.shared.application.domain.AggregateId
import org.egualpam.contexts.observable.shared.application.domain.AggregateRoot
import java.time.Instant
import java.util.UUID.randomUUID

class Order(private val id: OrderId) : AggregateRoot() {
  override fun getId(): AggregateId = id

  companion object {
    fun create(id: String): Order {
      val order = Order(id = OrderId(id))

      val event = OrderCreated.create(
          id = randomUUID().toString(),
          occurredOn = Instant.now(),
          order = order,
      )

      order.add(event)

      return order
    }

    fun load(id: String): Order = Order(OrderId(id))
  }
}
