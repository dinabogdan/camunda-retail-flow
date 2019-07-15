package com.freesoft.retail.payment.infrastructure

import java.io.Serializable
import java.math.BigDecimal

data class RetrievePaymentCommandPayload constructor(val refId: String?,
                                                     val reason: String?,
                                                     val amount: BigDecimal?) : Serializable {

    constructor() : this(null, null, null)
}