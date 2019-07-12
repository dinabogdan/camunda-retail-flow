package com.freesoft.retail.inventory.infrastructure

import com.freesoft.retail.inventory.domain.Item

data class FetchGoodsCommandPayload(val refId: String,
                                    val reason: String,
                                    val items: List<Item>)
