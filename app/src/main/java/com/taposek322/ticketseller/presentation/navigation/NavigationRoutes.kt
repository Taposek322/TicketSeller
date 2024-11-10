package com.taposek322.ticketseller.presentation.navigation

import com.taposek322.ticketseller.domain.Order
import kotlinx.serialization.Serializable

object NavigationRoutes {

    @Serializable
    data object ShoppingScreen

    @Serializable
    data class ShoppingCard(
        val orderList: List<Order>
    )

}