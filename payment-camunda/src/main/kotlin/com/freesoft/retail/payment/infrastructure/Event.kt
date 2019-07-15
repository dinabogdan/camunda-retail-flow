package com.freesoft.retail.payment.infrastructure

import java.io.Serializable
import java.util.*

data class Event<T> constructor(val type: String?,
                                val id: String?,
                                val traceId: String?,
                                val sender: String?,
                                val timestamp: Date?,
                                val correlationId: String?,
                                val payload: T?) : Serializable {

    constructor() : this(null, null, null, null, null, null, null)

    constructor(type: String,
                traceId: String,
                payload: T) : this(type,
            UUID.randomUUID().toString(),
            traceId,
            "Payment",
            Date(),
            null,
            payload)

    constructor(type: String?,
                traceId: String?,
                payload: T,
                correlationId: String?) : this(type,
            UUID.randomUUID().toString(),
            traceId,
            "Payment",
            Date(),
            correlationId,
            payload)
}