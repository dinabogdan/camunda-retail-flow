package com.freesoft.retail.shipping.application

import org.springframework.stereotype.Component
import java.util.*

@Component
class ShippingService {

    fun createShipment(pickId: String?,
                       recipientName: String?,
                       recipientAddress: String?,
                       logisticsProvider: String?): String {
        println("Shipping to: $recipientName at $recipientAddress")
        return UUID.randomUUID().toString()
    }
}