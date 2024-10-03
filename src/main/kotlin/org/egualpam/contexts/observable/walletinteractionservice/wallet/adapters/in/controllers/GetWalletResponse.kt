package org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.`in`.controllers

import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.usecases.query.WalletDto

data class GetWalletResponse(val wallet: Wallet) {
  data class Wallet(val id: String, val owner: Owner, val accounts: List<Account>)
  data class Owner(val id: String)
  data class Account(val id: String)

  companion object {
    fun from(walletDto: WalletDto) = GetWalletResponse(
        Wallet(
            walletDto.id,
            Owner(walletDto.owner.id),
            walletDto.accounts.map { Account(it.id) },
        ),
    )
  }
}
