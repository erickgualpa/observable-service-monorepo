package org.egualpam.contexts.observable.wallet.adapters.out.walletsearchrepository

import org.egualpam.contexts.observable.wallet.adapters.out.shared.springdatajdbc.WalletCrudRepository
import org.egualpam.contexts.observable.wallet.application.domain.WalletId
import org.egualpam.contexts.observable.wallet.application.ports.out.WalletSearchRepository
import org.egualpam.contexts.observable.wallet.application.usecases.query.WalletDto

class WalletSearchRepositorySpringDataJdbcAdapter(
  private var walletCrudRepository: WalletCrudRepository
) : WalletSearchRepository {
  override fun search(id: WalletId): WalletDto? {
    return walletCrudRepository.findByEntityId(id.value)?.toWalletDto()
  }
}

