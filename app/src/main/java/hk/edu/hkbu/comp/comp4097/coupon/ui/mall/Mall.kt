package hk.edu.hkbu.comp.comp4097.coupon.ui.mall

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hk.edu.hkbu.comp.comp4097.coupon.data.MallsViewModel

@ExperimentalMaterialApi
@Composable
fun Mall(nav: NavHostController) {
    Scaffold() {
        val data by MallsViewModel.data.observeAsState(listOf())

        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
            items(data, itemContent = { item ->
                ListItem(modifier = Modifier.clickable {
                    nav.navigate("restaurant/${item.mall}")
                }) {
                    Text(item.mall)
                }
                Divider()
            })
        }
    }
}