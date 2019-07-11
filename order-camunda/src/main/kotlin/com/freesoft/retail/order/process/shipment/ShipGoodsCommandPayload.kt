package com.freesoft.retail.order.process.shipment

data class ShipGoodsCommandPayload(val refId: String?,
                                   val pickId: String?,
                                   val logisticsProvider: String?,
                                   val recipientName: String?,
                                   val recipientAddress: String?)