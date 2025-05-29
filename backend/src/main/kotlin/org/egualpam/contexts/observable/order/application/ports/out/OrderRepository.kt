package org.egualpam.contexts.observable.order.application.ports.out

import org.egualpam.contexts.observable.order.application.domain.Order

interface OrderRepository {
  fun find(orderId: String): Order?
  fun save(order: Order)
}
