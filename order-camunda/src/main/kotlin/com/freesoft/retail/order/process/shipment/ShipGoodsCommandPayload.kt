package com.freesoft.retail.order.process.shipment

import java.io.Serializable

data class ShipGoodsCommandPayload(val refId: String?,
                                   val pickId: String?,
                                   val logisticsProvider: String?,
                                   val recipientName: String?,
                                   val recipientAddress: String?) : Serializable {

    constructor() : this(null, null, null, null, null)
}