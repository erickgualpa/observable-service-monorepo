package org.egualpam.contexts.observable.shared.adapters.out

import org.egualpam.contexts.observable.shared.adapters.metrics.DomainEventMetrics
import org.egualpam.contexts.observable.shared.application.domain.DomainEvent
import org.egualpam.contexts.observable.shared.application.ports.out.EventBus
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class FakeEventBus(
  private val domainEventMetrics: DomainEventMetrics,
) : EventBus {

  private val logger: Logger = getLogger(this::class.java)

  override fun publish(domainEvents: Set<DomainEvent>) {
    domainEvents.forEach {
      domainEventMetrics.published(it)
      logger.info("Fake publishing of event [${it.javaClass.simpleName}] with id [${it.id().value}]")
    }
  }
}
