package org.egualpam.contexts.observable.shared.adapters

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
      "org.egualpam.contexts.observable.shared.adapters.configuration",
      "org.egualpam.contexts.observable.wallet.adapters.configuration",
      "org.egualpam.contexts.observable.wallet.adapters.in.controllers",
      "org.egualpam.contexts.observable.order.adapters.configuration",
      "org.egualpam.contexts.observable.order.adapters.in",
    ],
)
class SharedApplication

fun main(args: Array<String>) {
  runApplication<SharedApplication>(*args)
}
