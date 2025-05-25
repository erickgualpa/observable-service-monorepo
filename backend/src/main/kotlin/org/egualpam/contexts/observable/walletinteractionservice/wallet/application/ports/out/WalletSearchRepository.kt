package org.egualpam.contexts.observable.walletinteractionservice.wallet.application.ports.out

import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain.WalletId
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.usecases.query.WalletDto

interface WalletSearchRepository {
  fun search(id: WalletId): WalletDto?
}
