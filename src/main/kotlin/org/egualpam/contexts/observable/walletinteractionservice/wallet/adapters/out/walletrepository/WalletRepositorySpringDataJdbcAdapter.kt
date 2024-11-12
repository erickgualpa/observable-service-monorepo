package org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.walletrepository

import org.egualpam.contexts.observable.walletinteractionservice.wallet.adapters.out.shared.springdatajdbc.WalletCrudRepository
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain.Account
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain.AccountId
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain.Deposit
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain.Wallet
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain.WalletId
import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.ports.out.WalletRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.time.Instant

open class WalletRepositorySpringDataJdbcAdapter(
  private val walletCrudRepository: WalletCrudRepository,
  private val jdbcTemplate: NamedParameterJdbcTemplate
) : WalletRepository {
  override fun find(walletId: WalletId): Wallet? {
    return walletCrudRepository.findByEntityId(walletId.value)?.toDomainEntity()
  }

  override fun save(wallet: Wallet) {
    walletCrudRepository.saveWallet(wallet.getId().value, Instant.now())
    walletCrudRepository.saveOwner(
        entityId = wallet.getOwnerId().value,
        createdAt = Instant.now(),
        username = wallet.getOwnerUsername().value,
        walletEntityId = wallet.getId().value,
    )
    wallet.accounts().forEach { account ->
      saveAccount(account, wallet.getId())
      account.deposits().forEach { deposit ->
        saveDeposit(deposit, account.getId())
      }
    }
  }

  private fun saveAccount(account: Account, walletId: WalletId) {
    val sql = """
      INSERT IGNORE INTO account(entity_id, created_at, currency, wallet_entity_id, wallet)
      SELECT :entityId, :createdAt, :currency, :walletEntityId, id
      FROM wallet
      WHERE entity_id=:walletEntityId
    """
    val sqlParameterSource = MapSqlParameterSource()
    sqlParameterSource.addValue("entityId", account.getId().value)
    sqlParameterSource.addValue("createdAt", Instant.now())
    sqlParameterSource.addValue("currency", account.getCurrency().value)
    sqlParameterSource.addValue("walletEntityId", walletId.value)
    jdbcTemplate.update(sql, sqlParameterSource)
  }

  private fun saveDeposit(deposit: Deposit, accountId: AccountId) {
    val sql = """
      INSERT IGNORE INTO deposit(entity_id, created_at, amount, account_entity_id, account)
      SELECT :entityId, :createdAt, :amount, :accountEntityId, id
      FROM account
      WHERE entity_id=:accountEntityId
    """
    val sqlParameterSource = MapSqlParameterSource()
    sqlParameterSource.addValue("entityId", deposit.getId().value)
    sqlParameterSource.addValue("createdAt", Instant.now())
    sqlParameterSource.addValue("amount", deposit.amount().value)
    sqlParameterSource.addValue("accountEntityId", accountId.value)
    jdbcTemplate.update(sql, sqlParameterSource)
  }
}

