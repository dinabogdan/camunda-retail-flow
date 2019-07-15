package com.freesoft.retail.order.process.payment

import java.io.Serializable
import java.math.BigDecimal

data class RetrievePaymentCommandPayload(val refId: String?,
                                         val reason: String?,
                                         val amount: BigDecimal) : Serializable {


    constructor(refId: String?, amount: BigDecimal) : this(refId, null, amount)
}