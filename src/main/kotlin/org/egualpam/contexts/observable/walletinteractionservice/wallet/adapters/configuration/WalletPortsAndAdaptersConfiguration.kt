package org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.configuration

import io.micrometer.core.instrument.MeterRegistry
import org.egualpam.contexts.observable.walletinteractionservice.shared.application.ports.out.EventBus
import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.eventbus.FakeEventBus
import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.metrics.DomainEventMetrics
import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.depositexists.DepositExistsMySQLAdapter
import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.shared.springdatajdbc.WalletCrudRepository
import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.walletexists.WalletExistsMySQLAdapter
import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.walletrepository.WalletRepositorySpringDataJdbcAdapter
import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.walletsearchrepository.WalletSearchRepositorySpringDataJdbcAdapter
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.ports.out.DepositExists
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.ports.out.WalletExists
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.ports.out.WalletRepository
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.ports.out.WalletSearchRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
class WalletPortsAndAdaptersConfiguration {
  @Primary
  @Bean
  fun walletSearchRepositorySpringDataJdbcAdapter(
    walletCrudRepository: WalletCrudRepository
  ): WalletSearchRepository = WalletSearchRepositorySpringDataJdbcAdapter(walletCrudRepository)

  @Bean
  fun walletRepositorySpringDataJdbcAdapter(
    walletCrudRepository: WalletCrudRepository,
    jdbcTemplate: NamedParameterJdbcTemplate
  ): WalletRepository = WalletRepositorySpringDataJdbcAdapter(
      walletCrudRepository,
      jdbcTemplate,
  )

  @Bean
  fun walletExistsMySQLAdapter(
    jdbcTemplate: NamedParameterJdbcTemplate
  ): WalletExists {
    return WalletExistsMySQLAdapter(jdbcTemplate)
  }

  @Bean
  fun depositExistsMySQLAdapter(
    jdbcTemplate: NamedParameterJdbcTemplate
  ): DepositExists = DepositExistsMySQLAdapter(jdbcTemplate)

  @Bean
  fun fakeEventBus(domainEventMetrics: DomainEventMetrics): EventBus =
      FakeEventBus(domainEventMetrics)

  @Bean
  fun walletDomainEventsMetrics(meterRegistry: MeterRegistry) =
      DomainEventMetrics(meterRegistry)
}

