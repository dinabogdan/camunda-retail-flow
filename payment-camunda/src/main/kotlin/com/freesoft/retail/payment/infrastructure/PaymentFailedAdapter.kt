package com.freesoft.retail.payment.infrastructure

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component
class PaymentFailedAdapter(val eventSender: EventSender) : JavaDelegate {

    override fun execute(context: DelegateExecution?) {

    }
}