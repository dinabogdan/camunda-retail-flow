package com.freesoft.retail.inventory.infrastructure

import java.io.Serializable

data class GoodsFetchedPayload(val refId: String?,
                               val pickId: String?) : Serializable