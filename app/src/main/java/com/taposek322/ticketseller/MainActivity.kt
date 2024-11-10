package com.taposek322.ticketseller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.taposek322.ticketseller.domain.Order
import com.taposek322.ticketseller.presentation.navigation.CustomNavType
import com.taposek322.ticketseller.presentation.navigation.NavigationRoutes
import com.taposek322.ticketseller.presentation.shopping_card.ShoppingCardContent
import com.taposek322.ticketseller.presentation.shopping_screen.ShoppingScreenContent
import com.taposek322.ticketseller.presentation.theme.TicketSellerTheme
import kotlin.reflect.typeOf

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicketSellerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.ShoppingScreen
                    ) {

                        composable<NavigationRoutes.ShoppingScreen> {
                            ShoppingScreenContent(
                                navController = navController,
                                modifier = Modifier
                                    .padding(innerPadding)
                            )
                        }

                        composable<NavigationRoutes.ShoppingCard>(
                            typeMap = mapOf(
                                typeOf<List<Order>>() to CustomNavType.OrdersListType
                            )
                        ) {
                            val arguments = it.toRoute<NavigationRoutes.ShoppingCard>()
                            ShoppingCardContent(
                                ordersList = arguments.orderList,
                                modifier = Modifier
                                    .padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}
