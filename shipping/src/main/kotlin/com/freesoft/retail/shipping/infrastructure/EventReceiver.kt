package com.freesoft.retail.shipping.infrastructure

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.freesoft.retail.shipping.application.ShippingService
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@EnableBinding(Sink::class)
class EventReceiver(private val eventSender: EventSender,
                    private val shippingService: ShippingService) {

    private val logger = LoggerFactory.getLogger(EventReceiver::class.java)

    @Transactional
    @StreamListener(target = Sink.INPUT,
            condition = "(headers['type']?:'')=='ShipGoodsCommand'")
    fun shipGoodsReceived(eventJson: String) {
        val event = ObjectMapper().readValue<Event<ShipGoodsCommandPayload>>(eventJson, object : TypeReference<Event<ShipGoodsCommandPayload>>() {})
        val payload = event.payload

        logger.info("Retrieve payment: {} for: {}", payload?.recipientAddress, payload?.refId)

        val shippmentId = shippingService.createShipment(payload?.pickId, payload?.recipientName, payload?.recipientAddress, payload?.logisticsProvider)

        eventSender.send(
                Event(
                        "GoodsShipped",
                        event.traceId!!,
                        GoodsShippedEventPayload(payload?.refId, shippmentId),
                        event.correlationId
                )
        )
    }
}