package org.egualpam.contexts.observable.walletinteractionservice.shared.adapters

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
      "org.egualpam.contexts.observable.walletinteractionservice.shared.adapters.configuration",
      "org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.configuration",
      "org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.in.controllers",
    ],
)
class WalletInteractionServiceApplication

fun main(args: Array<String>) {
  runApplication<WalletInteractionServiceApplication>(*args)
}
