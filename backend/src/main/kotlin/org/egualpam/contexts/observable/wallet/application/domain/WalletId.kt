package org.egualpam.contexts.observable.wallet.application.domain

import org.egualpam.contexts.observable.shared.application.domain.AggregateId

data class WalletId(val value: String) : AggregateId(value)
