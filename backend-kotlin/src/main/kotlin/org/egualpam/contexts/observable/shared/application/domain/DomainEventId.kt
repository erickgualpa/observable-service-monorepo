package org.egualpam.contexts.observable.shared.application.domain

import org.egualpam.contexts.observable.shared.application.domain.exceptions.InvalidDomainEntityId
import java.util.UUID

data class DomainEventId(val value: String) {
  init {
    try {
      UUID.fromString(value)
    } catch (e: IllegalArgumentException) {
      throw InvalidDomainEntityId(value)
    }
  }

  companion object {
    fun generate() = DomainEventId(UUID.randomUUID().toString())
  }
}
