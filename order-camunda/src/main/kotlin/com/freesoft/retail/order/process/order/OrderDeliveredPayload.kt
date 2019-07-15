package com.freesoft.retail.order.process.order

import java.io.Serializable

data class OrderDeliveredPayload(val orderId: String?) : Serializable {
    constructor() : this(null)
}