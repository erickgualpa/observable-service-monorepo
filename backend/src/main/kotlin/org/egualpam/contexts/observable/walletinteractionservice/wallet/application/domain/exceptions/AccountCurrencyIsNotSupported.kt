package org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain.exceptions

class AccountCurrencyIsNotSupported(value: String) : RuntimeException(
    "The provided currency [$value] is not supported",
)
