package org.egualpam.contexts.observable.wallet.application.ports.out

import org.egualpam.contexts.observable.wallet.application.domain.WalletId
import org.egualpam.contexts.observable.wallet.application.usecases.query.WalletDto

interface WalletSearchRepository {
  fun search(id: WalletId): WalletDto?
}
