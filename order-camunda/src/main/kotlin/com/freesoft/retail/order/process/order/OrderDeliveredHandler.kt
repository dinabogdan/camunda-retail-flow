package com.freesoft.retail.order.process.order

import com.freesoft.retail.order.infrastructure.Event
import com.freesoft.retail.order.infrastructure.EventSender
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component
class OrderDeliveredHandler(private val eventSender: EventSender) : JavaDelegate {

    override fun execute(context: DelegateExecution?) {
        val orderId = context?.getVariable("orderId") as String?
        val traceId = context?.processBusinessKey

        eventSender.send(
                Event(
                        "OrderDelivered",
                        traceId!!,
                        OrderDeliveredPayload(orderId)
                )
        )
    }
}