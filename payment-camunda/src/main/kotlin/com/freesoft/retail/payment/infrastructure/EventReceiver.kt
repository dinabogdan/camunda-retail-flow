package com.freesoft.retail.payment.infrastructure

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.camunda.bpm.engine.ProcessEngine
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@EnableBinding(Sink::class)
class EventReceiver(private val camunda: ProcessEngine,
                    private val eventSender: EventSender) {

    private val logger = LoggerFactory.getLogger(EventReceiver::class.java)

    @Transactional
    @StreamListener(target = Sink.INPUT, condition = "(headers['type']?:'')=='RetrievePaymentCommand'")
    fun receivePaymentCommand(objectJson: String) {
        val event = ObjectMapper()
                .readValue<Event<ReceivePaymentCommandPayload>>(objectJson,
                        object : TypeReference<Event<ReceivePaymentCommandPayload>>() {})

        val payload = event.payload

        logger.info("Retrieve payment: {} for: {}", payload?.amount, payload?.refId)

        camunda.runtimeService.createMessageCorrelation(event.type)
                .processInstanceBusinessKey(event.traceId)
                .setVariable("amount", payload?.amount)
                .setVariable("remainingAmount", payload?.amount)
                .setVariable("refId", payload?.refId)
                .setVariable("correlationId", event.correlationId)
                .correlateWithResult()

    }
}