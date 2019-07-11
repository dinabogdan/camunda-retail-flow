package com.freesoft.retail.order.domain

import org.springframework.data.repository.CrudRepository

interface OrderFactory : CrudRepository<Order, String?>