package org.egualpam.contexts.observable.wallet.application.domain

import org.egualpam.contexts.observable.shared.application.domain.DomainEntity

class Deposit private constructor(
  private val id: DepositId,
  private val amount: DepositAmount
) : DomainEntity() {
  override fun getId() = id

  companion object {
    fun create(
      id: String,
      amount: Double
    ) = Deposit(
        DepositId(id),
        DepositAmount(amount),
    )
  }

  fun amount() = amount
}
