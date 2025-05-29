package org.egualpam.contexts.observable.order.application.domain

import org.egualpam.contexts.observable.shared.application.domain.AggregateId
import org.egualpam.contexts.observable.shared.application.domain.AggregateRoot

class Order(private val id: OrderId) : AggregateRoot() {
  override fun getId(): AggregateId = id

  companion object {
    fun create(id: String): Order = Order(OrderId(id))
    fun load(id: String): Order = Order(OrderId(id))
  }
}
