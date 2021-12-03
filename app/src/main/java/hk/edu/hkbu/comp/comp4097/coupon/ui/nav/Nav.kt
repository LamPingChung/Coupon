package hk.edu.hkbu.comp.comp4097.coupon.ui.nav

import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hk.edu.hkbu.comp.comp4097.coupon.ui.coins.Coins
import hk.edu.hkbu.comp.comp4097.coupon.ui.coupon.Coupon
import hk.edu.hkbu.comp.comp4097.coupon.ui.home.Home
import hk.edu.hkbu.comp.comp4097.coupon.ui.login.Login
import hk.edu.hkbu.comp.comp4097.coupon.ui.mall.Mall
import hk.edu.hkbu.comp.comp4097.coupon.ui.map.Map
import hk.edu.hkbu.comp.comp4097.coupon.ui.restaurant.Restaurant
import hk.edu.hkbu.comp.comp4097.coupon.ui.restaurant.RestaurantCoins
import hk.edu.hkbu.comp.comp4097.coupon.ui.theme.CouponTheme
import hk.edu.hkbu.comp.comp4097.coupon.ui.user.User

@ExperimentalMaterialApi
@Composable
fun Nav() {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigation(modifier = Modifier.height(64.dp)) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.hierarchy

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = android.R.drawable.ic_dialog_info),
                        contentDescription = "Home"
                    )
                },
                label = { Text("Home") },
                selected = currentDestination?.any { it.route == "home" } == true,
                onClick = {
                    navController.navigate("home") {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = false
                    }
                }
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = android.R.drawable.ic_dialog_info),
                        contentDescription = "Mall"
                    )
                },
                label = { Text("Mall") },
                selected = currentDestination?.any { it.route == "mall" } == true,
                onClick = {
                    navController.navigate("mall") {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = false
                    }
                }
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = android.R.drawable.ic_dialog_info),
                        contentDescription = "Coins"
                    )
                },
                label = { Text("Coins") },
                selected = currentDestination?.any { it.route == "coins" } == true,
                onClick = {
                    navController.navigate("coins") {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = false
                    }
                }
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = android.R.drawable.ic_dialog_info),
                        contentDescription = "User"
                    )
                },
                label = { Text("User") },
                selected = currentDestination?.any { it.route == "user" } == true,
                onClick = {
                    navController.navigate("user") {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = false
                    }
                }
            )
        }
    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
        ) {
            composable("restaurant/{mall}") { backStackEntry ->
                val mall = backStackEntry.arguments?.getString("mall") ?: ""
                Restaurant(navController, mall)
            }
            composable("restaurantCoins/{str}") { backStackEntry ->
                val str = backStackEntry.arguments?.getString("str") ?: ""
                RestaurantCoins(navController, str)
            }
            composable("Coupon/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id") ?: ""
                Coupon(navController, id.toInt())
            }
            composable("map/{mall}") { backStackEntry ->
                val mall = backStackEntry.arguments?.getString("mall") ?: ""
                Map(navController, mall)
            }
            composable("login") { backStackEntry ->
                Login(navController)
            }
            composable("home") { Home(nav = navController) }
            composable("mall") { Mall(nav = navController) }
            composable("coins") { Coins(nav = navController) }
            composable("user") { User(nav = navController) }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CouponTheme {
        Nav()
    }
}