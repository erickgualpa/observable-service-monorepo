package org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.walletsearchrepository

import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.shared.springdatajdbc.WalletCrudRepository
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain.WalletId
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.ports.out.WalletSearchRepository
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.usecases.query.WalletDto

class WalletSearchRepositorySpringDataJdbcAdapter(
  private var walletCrudRepository: WalletCrudRepository
) : WalletSearchRepository {
  override fun search(id: WalletId): WalletDto? {
    return walletCrudRepository.findByEntityId(id.value)?.toWalletDto()
  }
}

