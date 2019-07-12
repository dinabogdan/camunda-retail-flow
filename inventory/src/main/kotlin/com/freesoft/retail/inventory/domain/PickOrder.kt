package com.freesoft.retail.inventory.domain

import reactor.core.publisher.toMono
import java.util.*

data class PickOrder(val pickId: String,
                     val items: List<Item>) {

    constructor(items: List<Item>) : this(UUID.randomUUID().toString(),
            items)
}

