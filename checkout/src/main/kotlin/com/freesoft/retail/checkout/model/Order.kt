package com.freesoft.retail.checkout.model

import java.io.Serializable
import java.math.BigDecimal
import java.util.function.Predicate

data class Order(val orderId: String,
                 val customer: Customer,
                 val items: MutableList<Item>) : Serializable {


    fun addItem(articleId: String, amount: BigDecimal) {
        val existingItem = removeItem(articleId)
        val item = Item(articleId, if (existingItem != null) amount.plus(existingItem.amount) else amount)
    }

    fun removeItem(articleId: String): Item? {
        items.forEach { item ->
            if (item.articleId == articleId) {
                items.remove(item)
                return item
            }
        }
        return null
    }
}