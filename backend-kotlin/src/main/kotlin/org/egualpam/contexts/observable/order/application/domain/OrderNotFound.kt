package org.egualpam.contexts.observable.order.application.domain

class OrderNotFound(id: String) : RuntimeException(
    "Order with id $id not found",
)
