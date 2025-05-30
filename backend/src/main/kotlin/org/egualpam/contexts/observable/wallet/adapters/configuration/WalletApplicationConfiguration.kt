package org.egualpam.contexts.observable.wallet.adapters.configuration

import org.egualpam.contexts.observable.shared.application.ports.out.EventBus
import org.egualpam.contexts.observable.wallet.application.ports.out.DepositExists
import org.egualpam.contexts.observable.wallet.application.ports.out.WalletExists
import org.egualpam.contexts.observable.wallet.application.ports.out.WalletRepository
import org.egualpam.contexts.observable.wallet.application.ports.out.WalletSearchRepository
import org.egualpam.contexts.observable.wallet.application.usecases.command.CreateWallet
import org.egualpam.contexts.observable.wallet.application.usecases.command.DepositMoney
import org.egualpam.contexts.observable.wallet.application.usecases.query.RetrieveWallet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WalletApplicationConfiguration {
  @Bean
  fun retrieveWallet(
    walletSearchRepository: WalletSearchRepository
  ) = RetrieveWallet(walletSearchRepository)

  @Bean
  fun createWallet(
      walletExists: WalletExists,
      walletRepository: WalletRepository,
      eventBus: EventBus
  ) = CreateWallet(walletExists, walletRepository, eventBus)

  @Bean
  fun depositMoney(
      depositExists: DepositExists,
      walletRepository: WalletRepository,
      eventBus: EventBus
  ) = DepositMoney(depositExists, walletRepository, eventBus)
}
