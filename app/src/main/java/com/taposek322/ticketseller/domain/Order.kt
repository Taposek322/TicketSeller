package com.taposek322.ticketseller.domain

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val name: String,
    val surname: String,
    val flightNumber: String,
    val price: Double,
    val adultTicketsCount: Int,
    val childrenTicketsCount: Int
)
