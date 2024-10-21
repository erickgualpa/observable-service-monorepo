package org.egualpam.contexts.observable.walletinteractionservice.shared.application.ports.out

import org.egualpam.contexts.observable.walletinteractionservice.shared.application.domain.DomainEvent

interface EventBus {
  fun publish(domainEvents: Set<DomainEvent>)
}
