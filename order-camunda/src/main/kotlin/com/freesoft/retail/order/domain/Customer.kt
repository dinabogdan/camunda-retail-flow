package com.freesoft.retail.order.domain

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "Customer")
data class Customer(@Id
                    @GeneratedValue(generator = "uuid2")
                    @GenericGenerator(name = "uuid2", strategy = "uuid2")
                    val id: String?,
                    val name: String?,
                    val address: String?)