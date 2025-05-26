package org.egualpam.contexts.observable.wallet.application.domain

import org.egualpam.contexts.observable.shared.application.domain.DomainEvent
import org.egualpam.contexts.observable.shared.application.domain.DomainEventId
import java.time.Instant

class DepositProcessed(wallet: Wallet) : DomainEvent(wallet) {
  private val id = DomainEventId.Companion.generate()
  private val occurredOn = Instant.now()

  override fun id() = this.id
  override fun occurredOn(): Instant = this.occurredOn
}
