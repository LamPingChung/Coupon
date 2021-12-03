package hk.edu.hkbu.comp.comp4097.coupon.ui.restaurant

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hk.edu.hkbu.comp.comp4097.coupon.data.CouponsViewModel
import hk.edu.hkbu.comp.comp4097.coupon.ui.coins.Coins

@ExperimentalMaterialApi
@Composable
fun RestaurantCoins(nav: NavHostController, str: String = "") {
    val value = str.split(",")
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(value[0]) },
            navigationIcon = {
                IconButton(onClick = { nav.popBackStack() }) {
                    // handler for physical back button
                    BackHandler(enabled = true, onBack = { nav.popBackStack() })
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) {

        val data by CouponsViewModel.data.observeAsState(listOf())
        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
            items(data.filter { it.coin < value[1].toInt() && it.coin > value[2].toInt() }
                , itemContent = { item ->
                ListItem(secondaryText = {
                    Text(item.mall)
                }, modifier = Modifier.clickable {
                    nav.navigate("Coupon/${item.id}")
                })  {
                    Text(item.restaurant)
                }
                Divider()
            })
        }
    }

}