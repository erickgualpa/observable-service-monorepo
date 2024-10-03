package org.egualpam.contexts.observable.walletinteractionservice.shared.application.domain

interface EventBus {
  fun publish(domainEvents: Set<DomainEvent>)
}
