package com.freesoft.retail.payment

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableProcessApplication
class PaymentCamundaApplication

fun main(args: Array<String>) {
    runApplication<PaymentCamundaApplication>(*args)
}