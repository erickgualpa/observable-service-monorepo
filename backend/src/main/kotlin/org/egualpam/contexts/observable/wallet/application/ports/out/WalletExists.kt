package org.egualpam.contexts.observable.wallet.application.ports.out

import org.egualpam.contexts.observable.wallet.application.domain.OwnerUsername
import org.egualpam.contexts.observable.wallet.application.domain.WalletId

interface WalletExists {
  fun with(walletId: WalletId): Boolean

  fun with(ownerUsername: OwnerUsername): Boolean
}
