package com.freesoft.retail.inventory.application

import com.freesoft.retail.inventory.domain.Item
import com.freesoft.retail.inventory.domain.PickOrder
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Component
class InventoryService {

    fun reserveGoods(items: List<Item>, reason: String, refId: String, expirationDate: LocalDateTime) = true

    fun pickItems(items: List<Item>, reason: String, refId: String): String {
        val pickOrder = PickOrder(items)
        return pickOrder.pickId
    }

}