package com.taposek322.ticketseller.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.taposek322.ticketseller.domain.Order
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val OrdersListType = object : NavType<List<Order>>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): List<Order>? {
            return Json.decodeFromString(bundle.getString(key) ?: return listOf())
        }

        override fun parseValue(value: String): List<Order> {
           return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<Order>): String {
            return Uri.encode(Json.encodeToString(value = value))
        }

        override fun put(bundle: Bundle, key: String, value: List<Order>) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }
}