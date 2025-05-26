package org.egualpam.contexts.observable.wallet.application.ports.out

import org.egualpam.contexts.observable.wallet.application.domain.DepositId

interface DepositExists {
  fun with(depositId: DepositId): Boolean
}
