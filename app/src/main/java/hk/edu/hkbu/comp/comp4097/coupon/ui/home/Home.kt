package hk.edu.hkbu.comp.comp4097.coupon.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import hk.edu.hkbu.comp.comp4097.coupon.data.CouponsViewModel



@ExperimentalCoilApi
@Composable
fun Home(nav: NavHostController) {
    Scaffold() {

        val data by CouponsViewModel.data.observeAsState(listOf())

        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
            items(data, itemContent = { item ->
                Spacer(Modifier.height(8.dp))
                Card(modifier = Modifier.fillMaxWidth().clickable{
                    nav.navigate("Coupon/${item.id}")
                }, elevation = 8.dp) {
                    Column {
                        Image(
                            painter = rememberImagePainter(item.image),
                            contentDescription = item.title,
                            modifier = Modifier.fillMaxWidth().aspectRatio(1.77f).clipToBounds(),
                            contentScale = ContentScale.Crop
                        )
                        Column(Modifier.padding(8.dp)) {
                            Text(item.restaurant, fontWeight = FontWeight.Bold)
                            Text(item.title)
                            Text("Coins: ${item.coin}")
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
            })
        }
    }
}