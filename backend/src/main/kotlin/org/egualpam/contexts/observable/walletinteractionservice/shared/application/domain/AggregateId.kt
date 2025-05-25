package org.egualpam.contexts.observable.walletinteractionservice.shared.application.domain

import org.egualpam.contexts.observable.walletinteractionservice.shared.application.domain.exceptions.InvalidAggregateId
import java.util.UUID

open class AggregateId(private val value: String) {
  init {
    try {
      UUID.fromString(value)
    } catch (e: IllegalArgumentException) {
      throw InvalidAggregateId(value)
    }
  }

  fun value() = value
}
