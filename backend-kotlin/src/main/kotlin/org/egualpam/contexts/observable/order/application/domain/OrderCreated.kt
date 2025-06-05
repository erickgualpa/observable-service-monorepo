package org.egualpam.contexts.observable.order.application.domain

import org.egualpam.contexts.observable.shared.application.domain.DomainEvent
import org.egualpam.contexts.observable.shared.application.domain.DomainEventId
import java.time.Instant

class OrderCreated(
  private val id: DomainEventId,
  private val occurredOn: Instant,
  order: Order
) :
  DomainEvent(order) {

  companion object {
    fun create(
      id: String,
      occurredOn: Instant,
      order: Order
    ) = OrderCreated(
        DomainEventId(id),
        occurredOn,
        order,
    )
  }

  override fun id() = id
  override fun occurredOn() = occurredOn
}
