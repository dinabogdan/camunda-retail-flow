package com.freesoft.retail.checkout.api

import com.freesoft.retail.checkout.infrastructure.Event
import com.freesoft.retail.checkout.infrastructure.EventSender
import com.freesoft.retail.checkout.model.Customer
import com.freesoft.retail.checkout.model.Item
import com.freesoft.retail.checkout.model.Order
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.*

@RestController
class ShopRestController(private val eventSender: EventSender) {

    @PutMapping(path = ["/api/cart/order"])
    fun placeOrder(@RequestParam(value = "customerId") customerId: String): String {
        val orderItems = mutableListOf<Item>()
        orderItems.add(Item("article1", BigDecimal.TEN))
        orderItems.add(Item("article2", BigDecimal.valueOf(20)))

        val customer = Customer("Dina", "Dragodanesti")

        val order = Order(UUID.randomUUID().toString(), customer, orderItems)

        val event = Event("OrderPlacedEvent", order)

        eventSender.send(event)

        return "{\"traceId\": \"" + event.traceId + "\"}"
    }
}