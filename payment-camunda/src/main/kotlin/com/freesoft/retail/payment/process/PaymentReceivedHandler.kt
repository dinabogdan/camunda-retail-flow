package com.freesoft.retail.payment.process

import com.freesoft.retail.payment.infrastructure.Event
import com.freesoft.retail.payment.infrastructure.EventSender
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component
class PaymentReceivedHandler(private val eventSender: EventSender) : JavaDelegate {

    override fun execute(context: DelegateExecution?) {
        val refId = context?.getVariable("refId") as String
        val correlationId = context.getVariable("correlationId") as String
        val traceId = context.getVariable("traceId") as String

        eventSender.send(
                Event(
                        "PaymentReceived",
                        traceId,
                        PaymentReceivedPayload(refId),
                        correlationId
                )
        )
    }
}