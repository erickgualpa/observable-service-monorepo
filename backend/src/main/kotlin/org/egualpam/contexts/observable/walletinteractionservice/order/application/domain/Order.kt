package org.egualpam.contexts.observable.walletinteractionservice.order.application.domain

import org.egualpam.contexts.observable.walletinteractionservice.shared.application.domain.AggregateId
import org.egualpam.contexts.observable.walletinteractionservice.shared.application.domain.AggregateRoot

class Order(private val id: OrderId) : AggregateRoot() {
  override fun getId(): AggregateId = id
}
