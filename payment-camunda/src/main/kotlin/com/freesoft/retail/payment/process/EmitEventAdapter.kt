package com.freesoft.retail.payment.process

import com.freesoft.retail.payment.infrastructure.Event
import com.freesoft.retail.payment.infrastructure.EventSender
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component
class EmitEventAdapter(private val eventSender: EventSender) : JavaDelegate {
    override fun execute(context: DelegateExecution?) {
        val traceId = context?.processBusinessKey

        eventSender.send(
                Event(context?.currentActivityId!!,
                        traceId!!,
                        null)
        )
    }
}