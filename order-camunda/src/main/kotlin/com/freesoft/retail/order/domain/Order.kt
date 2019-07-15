package com.freesoft.retail.order.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "OrderEntity")
data class Order(@Id
                 @GeneratedValue(generator = "uuid2")
                 @GenericGenerator(name = "uuid2", strategy = "uuid2")
                 val id: String?,
                 @OneToOne(cascade = [CascadeType.ALL],
                         fetch = FetchType.EAGER)
                 val customer: Customer,
                 @OneToMany(cascade = [CascadeType.ALL],
                         fetch = FetchType.EAGER)
                 val items: List<OrderItem>) {

    fun totalSum(): BigDecimal = items.sumByDouble { orderItem -> orderItem.amount.toDouble() }.toBigDecimal()
}