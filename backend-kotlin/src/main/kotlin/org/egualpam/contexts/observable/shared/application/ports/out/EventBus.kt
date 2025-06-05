package org.egualpam.contexts.observable.shared.application.ports.out

import org.egualpam.contexts.observable.shared.application.domain.DomainEvent

interface EventBus {
  fun publish(domainEvents: Set<DomainEvent>)
}
