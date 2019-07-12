package com.freesoft.retail.order.process.goods

import com.freesoft.retail.order.domain.OrderItem

data class FetchGoodsCommandPayload(val refId: String?,
                                    val reason: String,
                                    val items: List<OrderItem>) {

    constructor(refId: String?, items: List<OrderItem>) :
            this(refId, "CustomerOrder", items)
}