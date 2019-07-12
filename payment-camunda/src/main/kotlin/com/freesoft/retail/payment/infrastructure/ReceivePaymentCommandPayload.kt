package com.freesoft.retail.payment.infrastructure

import java.math.BigDecimal

data class ReceivePaymentCommandPayload(val refId: String,
                                        val reason: String,
                                        val amount: BigDecimal)