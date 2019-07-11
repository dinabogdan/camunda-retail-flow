package com.freesoft.retail.checkout.api

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Controller
class TomcatConfiguration {

    @Bean
    fun forwardToIndex(): WebMvcConfigurerAdapter {
        return object : WebMvcConfigurerAdapter() {
            override fun addViewControllers(registry: ViewControllerRegistry) {
                registry.addViewController("/").setViewName("forward:/shop.html")
            }
        }
    }
}