package org.egualpam.contexts.observable.shared.adapters.metrics

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.egualpam.contexts.observable.shared.application.domain.DomainEvent

class DomainEventMetrics(
  private val meterRegistry: MeterRegistry
) {
  fun published(domainEvent: DomainEvent) {
    Counter.builder("application_domain_events_published")
        .tag("type", domainEvent.javaClass.simpleName)
        .tag("app", "order-service-backend-kotlin")
        .register(meterRegistry)
        .increment()
  }
}
