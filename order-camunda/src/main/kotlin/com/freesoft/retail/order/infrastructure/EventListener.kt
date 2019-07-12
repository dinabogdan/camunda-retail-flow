package com.freesoft.retail.order.infrastructure

import com.freesoft.retail.order.domain.Order
import com.freesoft.retail.order.domain.OrderFactory
import org.camunda.bpm.engine.ProcessEngine
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component
import javax.transaction.Transactional

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

}