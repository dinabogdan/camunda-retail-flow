package com.freesoft.retail.payment.infrastructure

import java.math.BigDecimal

data class RetrievePaymentCommandPayload(val refId: String?,
                                         val reason: String?,
                                         val amount: BigDecimal?) {

    constructor() : this(null, null, null)

}