package com.taposek322.ticketseller.presentation.shopping_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taposek322.ticketseller.domain.Order

@Composable
fun ShoppingCardContent(
    ordersList: List<Order>,
    modifier: Modifier = Modifier
) {
    ShoppingCardScreen(
        ordersList = ordersList,
        modifier = modifier
    )
}

@Composable
fun ShoppingCardScreen(
    ordersList: List<Order>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Total tickets price: ${ordersList.sumOf { it.price }}"
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        Text(
            text = "Total adult tickets count: ${ordersList.sumOf { it.adultTicketsCount }}"
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        Text(
            text = "Total children tickets count: ${ordersList.sumOf { it.childrenTicketsCount }}"
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        LazyColumn {
            items(ordersList) {
                ListItemContainer(it)
            }
        }
    }
}

@Composable
fun ListItemContainer(
    order: Order,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = "Name: ${order.name}",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Surname: ${order.surname}",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Flight number: ${order.flightNumber}",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Price: ${order.price}",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Adult tickets count: ${order.adultTicketsCount}",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Children tickets count: ${order.childrenTicketsCount}",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
@Preview
private fun ShoppingCardScreenPreview() {
    ShoppingCardScreen(
        listOf()
    )
}
