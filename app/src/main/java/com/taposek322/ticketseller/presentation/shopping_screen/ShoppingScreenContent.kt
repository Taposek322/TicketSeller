package com.taposek322.ticketseller.presentation.shopping_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.taposek322.ticketseller.domain.Order
import com.taposek322.ticketseller.presentation.navigation.NavigationRoutes
import com.taposek322.ticketseller.presentation.theme.TicketSellerTheme

@Composable
fun ShoppingScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ShoppingScreenViewModel = viewModel()
) {
    val orderList by viewModel.orderList.collectAsState()
    ShoppingScreen(
        orderListSize = orderList.size,
        onNextClick = { viewModel.addOrderToList(it) },
        onFinishClick = { navController.navigate(NavigationRoutes.ShoppingCard(orderList)) },
        modifier = modifier
    )
}

@Composable
fun ShoppingScreen(
    orderListSize: Int,
    onNextClick: (Order) -> Unit,
    onFinishClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var nameError by rememberSaveable {
        mutableStateOf("")
    }

    var surname by rememberSaveable {
        mutableStateOf("")
    }
    var surnameError by rememberSaveable {
        mutableStateOf("")
    }

    var flightNumber by rememberSaveable {
        mutableStateOf("")
    }
    var flightNumberError by rememberSaveable {
        mutableStateOf("")
    }

    var price by rememberSaveable {
        mutableStateOf("")
    }
    var priceError by rememberSaveable {
        mutableStateOf("")
    }

    var adultTicketsCount by rememberSaveable {
        mutableStateOf("")
    }
    var adultTicketsCountError by rememberSaveable {
        mutableStateOf("")
    }

    var childrenTicketsCount by rememberSaveable {
        mutableStateOf("")
    }
    var childrenTicketsCountError by rememberSaveable {
        mutableStateOf("")
    }

    var isValid by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Inputed orders count: $orderListSize",
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier
                .height(12.dp)
        )
        InputField(
            text = "Name",
            value = name,
            onValueChanged = { name = it },
            keyboardType = KeyboardType.Text,
            errorText = nameError
        )
        InputField(
            text = "Surname:",
            value = surname,
            onValueChanged = { surname = it },
            keyboardType = KeyboardType.Text,
            errorText = surnameError
        )
        InputField(
            text = "Flight Number:",
            value = flightNumber,
            onValueChanged = { flightNumber = it },
            keyboardType = KeyboardType.Text,
            errorText = flightNumberError
        )
        InputField(
            text = "Price:",
            value = price,
            onValueChanged = { price = it },
            keyboardType = KeyboardType.Decimal,
            errorText = priceError
        )
        InputField(
            text = "Adults tickets count:",
            value = adultTicketsCount,
            onValueChanged = { adultTicketsCount = it },
            keyboardType = KeyboardType.Decimal,
            errorText = adultTicketsCountError
        )
        InputField(
            text = "Children tickets count:",
            value = childrenTicketsCount,
            onValueChanged = { childrenTicketsCount = it },
            keyboardType = KeyboardType.Decimal,
            errorText = surnameError
        )
        Button(
            onClick = {
                nameError = ""
                surnameError = ""
                flightNumberError = ""
                priceError = ""
                adultTicketsCountError = ""
                childrenTicketsCountError = ""
                isValid = true
                if (name.isEmpty()) {
                    nameError = "Field can`t be empty"
                    isValid = false
                }
                if (surname.isEmpty()) {
                    surnameError = "Field can`t be empty"
                    isValid = false
                }
                if (flightNumber.isEmpty()) {
                    flightNumberError = "Field can`t be empty"
                    isValid = false
                }
                if (price.isEmpty()) {
                    priceError = "Field can`t be empty"
                    isValid = false
                }
                if (adultTicketsCount.isEmpty() || !adultTicketsCount.all { it.isDigit() }) {
                    isValid = false
                    if (adultTicketsCount.isEmpty()) {
                        adultTicketsCountError = "Field can`t be empty"
                    }
                    if (!adultTicketsCount.all { it.isDigit() }) {
                        adultTicketsCountError = "Count must be an integer"
                    }

                }
                if (childrenTicketsCount.isEmpty() || !childrenTicketsCount.all { it.isDigit() }) {
                    isValid = false
                    if (childrenTicketsCount.isEmpty()) {
                        childrenTicketsCountError = "Field can`t be empty"
                    }
                    if (!childrenTicketsCount.all { it.isDigit() }) {
                        childrenTicketsCountError = "Count must be an integer"
                    }

                }
                if (isValid) {
                    val order = Order(
                        name,
                        surname,
                        flightNumberError,
                        price.toDouble(),
                        adultTicketsCount.toInt(),
                        childrenTicketsCount.toInt()
                    )
                    onNextClick(order)
                    name = ""
                    surname = ""
                    flightNumber = ""
                    price = ""
                    adultTicketsCount = ""
                    childrenTicketsCount = ""
                }
            }
        ) {
            Text("Next order")
        }
        Button(
            onClick = { onFinishClick() }
        ) {
            Text("Finish")
        }
    }
}

@Composable
fun InputField(
    text: String,
    errorText: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(24.dp)
    ) {
        Text(
            text = text
        )
        Spacer(
            modifier = Modifier
                .width(8.dp)
        )
        TextField(
            value = value,
            onValueChange = { onValueChanged(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = errorText.isNotEmpty(),
            supportingText = {
                if (errorText.isNotEmpty()) {
                    Text(text = errorText)
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ShoppingScreenPreview() {
    TicketSellerTheme {
        ShoppingScreen(
            orderListSize = 2,
            onNextClick = {},
            onFinishClick = {}
        )
    }
}