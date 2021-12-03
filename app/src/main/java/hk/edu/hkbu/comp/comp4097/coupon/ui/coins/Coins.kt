package hk.edu.hkbu.comp.comp4097.coupon.ui.coins

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class Coins (val name: String, val lessThan: Int, val moreThan: Int) {
    companion object {
        val data = listOf(
            Coins("Coins <= 300", 301, -1),
            Coins("300 < Coins < 600", 600, 300),
            Coins("Coins >= 600", 1000000000, 599)
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun Coins(nav: NavHostController) {
    Scaffold() {

        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
            items(Coins.data, itemContent = { item ->
                ListItem(modifier = Modifier.clickable {
                    nav.navigate("restaurantCoins/${item.name},${item.lessThan},${item.moreThan}")
                }) {
                    Text(item.name)
                }
                Divider()
            })
        }
    }
}