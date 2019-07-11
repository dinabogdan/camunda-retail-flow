package com.freesoft.retail.payment.infrastructure

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@EnableBinding(Sink::class)
class EventReceiver(private val eventSender: EventSender) {

    private val logger = LoggerFactory.getLogger(EventReceiver::class.java)

    @Transactional
    @StreamListener(target = Sink.INPUT,
            condition = "(headers['type']?:'')=='RetrievePaymentCommand'")
    fun receivePaymentCommand(eventJson: String) {
        val event = ObjectMapper()
                .readValue<Event<ReceivePaymentCommandPayload>>(eventJson,
                        object : TypeReference<Event<ReceivePaymentCommandPayload>>() {})

        val commandPayload = event.payload

        logger.info("Retrieve payment: {} for: {}", commandPayload?.amount, commandPayload?.refId)

        eventSender.send(
                Event(
                        "PaymentReceivedEvent",
                        event.traceId,
                        PaymentReceivedPayload(commandPayload?.refId!!),
                        event.correlationId
                )
        )
    }
}