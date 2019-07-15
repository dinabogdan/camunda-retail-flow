package com.freesoft.retail.inventory.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Item(val articleId: String?,
                val amount: String?) : Serializable {

    constructor() : this(null, null)

}