package org.egualpam.contexts.observable.shared.adapters.metrics

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry

class ErrorMetrics(
  private val meterRegistry: MeterRegistry
) {
  fun error(cause: Exception) {
    Counter.builder("application_error_occurred")
        .tag("type", cause.javaClass.simpleName)
        .register(meterRegistry)
        .increment()
  }
}
