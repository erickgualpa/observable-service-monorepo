package org.egualpam.contexts.observable.wallet.application.domain

import org.egualpam.contexts.observable.shared.application.domain.DomainEvent
import org.egualpam.contexts.observable.shared.application.domain.DomainEventId
import java.time.Instant

class WalletCreated(wallet: Wallet) : DomainEvent(wallet) {
  private val id = DomainEventId.Companion.generate()
  private val occurredOn = Instant.now()

  override fun id() = id
  override fun occurredOn(): Instant = occurredOn
}
