package org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain

import org.egualpam.contexts.observable.walletinteractionservice.shared.application.domain.DomainEntityId


data class AccountId(val value: String) : DomainEntityId(value)
