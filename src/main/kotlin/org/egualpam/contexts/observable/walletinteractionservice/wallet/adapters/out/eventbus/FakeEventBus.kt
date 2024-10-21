package org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.eventbus

import org.egualpam.contexts.observable.walletinteractionservice.shared.application.domain.DomainEvent
import org.egualpam.contexts.observable.walletinteractionservice.shared.application.ports.out.EventBus
import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.metrics.DomainEventMetrics
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FakeEventBus(
  private val domainEventMetrics: DomainEventMetrics,
) : EventBus {

  private val logger: Logger = LoggerFactory.getLogger(this::class.java)

  override fun publish(domainEvents: Set<DomainEvent>) {
    domainEvents.forEach {
      domainEventMetrics.published(it)
      logger.info("Fake publishing of event [${it.javaClass.simpleName}] with id [${it.id().value}]")
    }
  }
}
