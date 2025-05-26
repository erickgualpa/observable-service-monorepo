package org.egualpam.contexts.observable.walletinteractionservice.order.application.domain

import org.egualpam.contexts.observable.walletinteractionservice.shared.application.domain.AggregateId

data class OrderId(val value: String) : AggregateId(value)
