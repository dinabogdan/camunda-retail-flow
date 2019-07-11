package com.freesoft.retail.order.domain

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "OrderEntity")
data class Order(@Id val id: String?,
                 @OneToOne(cascade = [CascadeType.ALL],
                         fetch = FetchType.EAGER)
                 val customer: Customer,
                 @OneToMany(cascade = [CascadeType.ALL],
                         fetch = FetchType.EAGER)
                 val items: List<OrderItem>) {

    fun totalSum(): BigDecimal = items.sumByDouble { orderItem -> orderItem.amount.toDouble() }.toBigDecimal()
}