package com.freesoft.retail.checkout.infrastructure

import java.util.*

data class Event<T>(val type: String,
                    val id: String,
                    val traceId: String,
                    val sender: String,
                    val timestamp: Date,
                    val correlationId: String?,
                    val payload: T) {

    constructor(type: String, payload: T) : this(type,
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            "Checkout",
            Date(),
            null,
            payload)
}
