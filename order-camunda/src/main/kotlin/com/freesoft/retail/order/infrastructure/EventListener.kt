package com.freesoft.retail.order.infrastructure

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.freesoft.retail.order.domain.Order
import com.freesoft.retail.order.domain.OrderFactory
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.spin.plugin.variable.SpinValues
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@EnableBinding(Sink::class)
class EventListener(private val orderFactory: OrderFactory,
                    private val camunda: ProcessEngine) {

    companion object {
        val logger = LoggerFactory.getLogger(EventListener::class.java)
    }

    @Transactional
    @StreamListener(target = Sink.INPUT, condition = "(headers['type']?:'') == 'OrderPlaced'")
    fun orderPlaced(event: Event<Order>) {
        val order = event.payload

        logger.info("Order: {} was placed. A new process was started.", order.toString())

        orderFactory.save(order)

        camunda.runtimeService
                .createMessageCorrelation(event.type)
                .processInstanceBusinessKey(event.traceId)
                .setVariable("orderId", order?.id)
                .correlateWithResult()
    }

    @Transactional
    @StreamListener(target = Sink.INPUT, condition = "(headers['type']?:'').endsWith('ed')")
    fun eventReceived(eventJson: String) {
        val event = ObjectMapper().readValue<Event<JsonNode>>(eventJson, object : TypeReference<Event<JsonNode>>() {})

        val correlatingInstances = camunda.runtimeService.createExecutionQuery()
                .messageEventSubscriptionName(event.type)
                .processInstanceBusinessKey(event.traceId)
                .count()

        if (correlatingInstances == 1L) {
            logger.info("Correlating {} to waiting flow instance", event)

            camunda.runtimeService.createMessageCorrelation(event.type)
                    .processInstanceBusinessKey(event.traceId)
                    .setVariable("PAYLOAD_" + event.type, SpinValues.jsonValue(event.payload.toString()).create())
                    .correlateWithResult()
        }
    }
}