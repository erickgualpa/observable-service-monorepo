package org.egualpam.contexts.observable.order.application.domain

import org.egualpam.contexts.observable.shared.application.domain.AggregateId
import org.egualpam.contexts.observable.shared.application.domain.AggregateRoot

class Order(private val id: OrderId) : AggregateRoot() {
  override fun getId(): AggregateId = id
}
