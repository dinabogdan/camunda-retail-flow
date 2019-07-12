package com.freesoft.retail.order.domain

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "OrderItem")
data class OrderItem(@Id
                     @GeneratedValue(generator = "uuid2")
                     @GenericGenerator(name = "uuid2", strategy = "uuid2")
                     val id: String?,
                     val articleId: String?,
                     val amount: BigDecimal)