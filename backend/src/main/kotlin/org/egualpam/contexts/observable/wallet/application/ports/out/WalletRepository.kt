package org.egualpam.contexts.observable.wallet.application.ports.out

import org.egualpam.contexts.observable.wallet.application.domain.Wallet
import org.egualpam.contexts.observable.wallet.application.domain.WalletId

interface WalletRepository {
  fun find(walletId: WalletId): Wallet?
  fun save(wallet: Wallet)
}
