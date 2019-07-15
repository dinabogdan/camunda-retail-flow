package com.freesoft.retail.inventory.infrastructure

import com.freesoft.retail.inventory.domain.Item
import java.io.Serializable

data class FetchGoodsCommandPayload(val refId: String?,
                                    val reason: String?,
                                    val items: List<Item>?) : Serializable {

    constructor() : this(null, null, null)

}
