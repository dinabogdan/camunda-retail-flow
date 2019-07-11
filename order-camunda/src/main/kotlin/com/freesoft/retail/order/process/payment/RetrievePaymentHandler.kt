package com.freesoft.retail.order.process.payment

import com.freesoft.retail.order.domain.OrderFactory
import com.freesoft.retail.order.infrastructure.Event
import com.freesoft.retail.order.infrastructure.EventSender
import com.freesoft.retail.order.process.payment.RetrievePaymentCommandPayload
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component
class RetrievePaymentHandler(private val eventSender: EventSender,
                             private val orderFactory: OrderFactory) : JavaDelegate {

    override fun execute(context: DelegateExecution?) {
        val orderId = context?.getVariable("orderId") as String
        val order = orderFactory.findById(orderId).get()
        val traceId = context.processBusinessKey

        eventSender.send(
                Event(
                        "RetrievePaymentCommand",
                        traceId,
                        RetrievePaymentCommandPayload(
                                order.id,
                                order.totalSum()
                        )
                )
        )
    }
}