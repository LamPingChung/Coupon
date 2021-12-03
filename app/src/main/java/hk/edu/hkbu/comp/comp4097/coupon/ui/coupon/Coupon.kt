package hk.edu.hkbu.comp.comp4097.coupon.ui.coupon

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import hk.edu.hkbu.comp.comp4097.coupon.data.CouponsViewModel

@ExperimentalCoilApi
@Composable
fun Coupon(nav: NavHostController, id:Int = 0) {
    val data by CouponsViewModel.data.observeAsState(listOf())
    data.forEach {
        if(it.id == id) {
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text(it.restaurant) },
                    navigationIcon = {
                        IconButton(onClick = { nav.popBackStack() }) {
                            // handler for physical back button
                            BackHandler(enabled = true, onBack = { nav.popBackStack() })
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }) {


                LazyColumn() {
                    items(data.filter { it.id == id }, itemContent = { item ->
                        Image(
                            painter = rememberImagePainter(item.image),
                            contentDescription = item.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.77f)
                                .clipToBounds(),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.height(8.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(), elevation = 8.dp,
                            border = BorderStroke(
                                2.dp,
                                Color.Black
                            ),
                        ) {
                            Column(Modifier.padding(8.dp)) {
                                Text(item.restaurant, fontWeight = FontWeight.Bold)
                                Text(item.title)
                                Text("Mall: ${item.mall} Coins: ${item.coin}")
                                Text("Expiry Date: ${item.expirydate}")
                                Row() {
                                    Button(onClick = { /*TODO*/ }, Modifier.width(150.dp)) {
                                        Text("Redeem")
                                    }
                                    Spacer(Modifier.width(30.dp))
                                    Button(
                                        onClick = { nav.navigate("map/${item.mall}") },
                                        Modifier.width(150.dp)
                                    ) {
                                        Text("Address")
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                    })
                }
            }
        }
    }
}