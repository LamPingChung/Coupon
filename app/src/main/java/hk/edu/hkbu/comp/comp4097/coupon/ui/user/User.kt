package hk.edu.hkbu.comp.comp4097.coupon.ui.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hk.edu.hkbu.comp.comp4097.coupon.R
import hk.edu.hkbu.comp.comp4097.coupon.data.MallsViewModel

@ExperimentalMaterialApi
@Composable
fun User(nav: NavHostController) {

    val data by MallsViewModel.data.observeAsState(listOf())
    Scaffold() {
        Column() {
            Spacer(modifier = Modifier.heightIn(10.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Card(
                    border = BorderStroke(
                        2.dp,
                        Color.Black
                    ),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Logo",
                        modifier = Modifier.width(256.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text("dsadasdsa")
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
                item {
                    Column(modifier = Modifier.clickable{nav.navigate("login")}) {
                        Text("Login")
                        Divider()
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item() {
                    Column(modifier = Modifier.clickable{}) {
                        Text("My Redeemed Coupon")
                        Divider()
                    }
                }
            }
        }
    }
}