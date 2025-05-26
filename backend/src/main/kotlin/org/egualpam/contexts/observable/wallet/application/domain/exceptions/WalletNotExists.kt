package org.egualpam.contexts.observable.wallet.application.domain.exceptions

import org.egualpam.contexts.observable.wallet.application.domain.WalletId

class WalletNotExists(
  walletId: WalletId
) : RuntimeException("Wallet with id [${walletId.value}] not exists")
