package com.freesoft.retail.shipping

import com.freesoft.retail.shipping.application.ShippingService
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class ShippingTest {

    @Autowired
    lateinit var shippingService: ShippingService

    @Test
    fun contextLoad() {
        assertNotNull(shippingService)
    }

}