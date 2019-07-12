package com.freesoft.retail.shipping.infrastructure

import java.math.BigDecimal

data class RetrievePaymentCommandPayload(val refId: String,
                                         val reason: String,
                                         val amount: BigDecimal)