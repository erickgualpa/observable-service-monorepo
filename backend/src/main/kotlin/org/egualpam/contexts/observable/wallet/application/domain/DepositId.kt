package org.egualpam.contexts.observable.wallet.application.domain

import org.egualpam.contexts.observable.shared.application.domain.DomainEntityId

data class DepositId(val value: String) : DomainEntityId(value)
