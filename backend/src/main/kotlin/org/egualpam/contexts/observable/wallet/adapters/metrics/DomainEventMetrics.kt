package org.egualpam.contexts.observable.wallet.adapters.metrics

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.egualpam.contexts.observable.shared.application.domain.DomainEvent

class DomainEventMetrics(
  private val meterRegistry: MeterRegistry
) {
  fun published(domainEvent: DomainEvent) {
    Counter.builder("wallet_domain_events_published")
        .tag("type", domainEvent.javaClass.simpleName)
        .register(meterRegistry)
        .increment()
  }
}
