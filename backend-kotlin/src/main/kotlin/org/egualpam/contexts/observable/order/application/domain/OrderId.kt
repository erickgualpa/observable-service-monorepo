package org.egualpam.contexts.observable.order.application.domain

import org.egualpam.contexts.observable.shared.application.domain.AggregateId

data class OrderId(val value: String) : AggregateId(value)
