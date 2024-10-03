package org.egualpam.contexts.observable.walletinteractionservice.wallet.application.ports.out

import org.egualpam.contexts.observable.walletinteractionservice.wallet.application.domain.DepositId

interface DepositExists {
  fun with(depositId: DepositId): Boolean
}
