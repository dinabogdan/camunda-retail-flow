package com.freesoft.demoflowingretailcamunda.process.goods

import com.freesoft.demoflowingretailcamunda.domain.OrderItem

data class FetchGoodsCommandPayload(val refId: String?,
                                    val reason: String,
                                    val items: List<OrderItem>) {

    constructor(refId: String?, items: List<OrderItem>) :
            this(refId, "CustomerOrder", items)
}