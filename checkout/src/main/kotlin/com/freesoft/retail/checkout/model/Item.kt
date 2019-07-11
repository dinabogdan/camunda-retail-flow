package com.freesoft.retail.checkout.model

import java.math.BigDecimal

data class Item(val articleId: String,
                val amount: BigDecimal)