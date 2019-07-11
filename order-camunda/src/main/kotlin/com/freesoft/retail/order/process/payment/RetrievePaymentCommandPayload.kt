package com.freesoft.retail.order.process.payment

import java.math.BigDecimal

data class RetrievePaymentCommandPayload(private val refId: String?,
                                         private val reason: String?,
                                         private val amount: BigDecimal) {


    constructor(refId: String?, amount: BigDecimal) : this(refId, null, amount)
}