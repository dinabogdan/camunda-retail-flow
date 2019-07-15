package com.freesoft.retail.order.process.shipment

import com.freesoft.retail.order.domain.OrderFactory
import com.freesoft.retail.order.infrastructure.Event
import com.freesoft.retail.order.infrastructure.EventSender
import com.freesoft.retail.order.process.shipment.ShipGoodsCommandPayload
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component
class ShipGoodsHandler(private val eventSender: EventSender,
                       private val orderFactory: OrderFactory) : JavaDelegate {

    override fun execute(context: DelegateExecution?) {
        val orderId = context?.getVariable("orderId") as String?
        val order = orderFactory.findById(orderId).get()
        val pickId = context?.getVariable("pickId") as String?
        val traceId = context?.processBusinessKey

        eventSender.send(
                Event(
                        "ShipGoodsCommand",
                        traceId!!,
                        ShipGoodsCommandPayload(
                                order.id,
                                pickId,
                                "Logitex",
                                order.customer.name,
                                order.customer.address
                        )
                )
        )
    }
}