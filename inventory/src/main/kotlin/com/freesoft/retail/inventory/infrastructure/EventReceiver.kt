package com.freesoft.retail.inventory.infrastructure

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.freesoft.retail.inventory.application.InventoryService
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@EnableBinding(Sink::class)
class EventReceiver(private val eventSender: EventSender,
                    private val inventoryService: InventoryService) {

    @Transactional
    @StreamListener(target = Sink.INPUT,
            condition = "(headers['type']?:'')=='FetchGoodsCommand'")
    fun retrivePaymentReceived(eventJson: String) {
        val event = ObjectMapper().readValue<Event<FetchGoodsCommandPayload>>(eventJson, object : TypeReference<Event<FetchGoodsCommandPayload>>() {})

        val payload = event.payload

        val pickId = inventoryService.pickItems(payload?.items!!, payload.reason!!, payload.refId!!)

        eventSender.send(
                Event(
                        "GoodsFetched",
                        event.traceId!!,
                        GoodsFetchedPayload(
                                payload.refId,
                                pickId
                        ),
                        event.correlationId!!
                )
        )
    }
}