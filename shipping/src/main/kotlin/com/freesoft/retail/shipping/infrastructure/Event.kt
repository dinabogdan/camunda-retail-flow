package com.freesoft.retail.shipping.infrastructure

import java.util.*

data class Event<T>(val type: String?,
                    val id: String?,
                    val traceId: String?,
                    val sender: String?,
                    val timestamp: Date?,
                    val correlationId: String?,
                    val payload: T?) {

    constructor() : this(null, null, null, null, null, null, null)

    constructor(type: String,
                traceId: String,
                payload: T,
                correlationId: String?) : this(type,
            UUID.randomUUID().toString(),
            traceId,
            "Shipping",
            Date(),
            correlationId,
            payload)
}