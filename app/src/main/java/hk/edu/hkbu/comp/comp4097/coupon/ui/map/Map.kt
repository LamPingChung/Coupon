package hk.edu.hkbu.comp.comp4097.coupon.ui.map

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import hk.edu.hkbu.comp.comp4097.coupon.data.CouponsViewModel
import hk.edu.hkbu.comp.comp4097.coupon.data.MallsViewModel

//data class Building(val title: String, val latitude: Double, val longitude: Double) {
//    companion object {
//        val data = listOf(
//            Building("AC Hall", 22.341280, 114.179768),
//            Building("Lam Woo International Conference Center", 22.337716, 114.182013),
//            Building("Communication and Visual Arts Building", 22.334382, 114.182528)
//        )
//    }
//}

@Composable
fun Map(nav: NavHostController, mall: String = "") {

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(mall) },
            navigationIcon = {
                IconButton(onClick = { nav.popBackStack() }) {
                    // handler for physical back button
                    BackHandler(enabled = true, onBack = { nav.popBackStack() })
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) {

        val data by MallsViewModel.data.observeAsState(listOf())
        data.forEach {
            if (it.mall == mall) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(), // Occupy the max size in the Compose UI tree
                    factory = { context ->
                        MapView(context).apply {
                            onCreate(null)
                            getMapAsync { map ->
                                map.moveCamera(
                                    CameraUpdateFactory.newLatLngZoom(
                                        LatLng(it.latitude, it.longitude),
                                        15f
                                    )
                                )
                                map.addMarker(
                                    MarkerOptions().position(
                                        LatLng(it.latitude, it.longitude)
                                    ).title(it.mall)
                                )

                            }
                        }
                    },
                    update = { view -> view.onResume() }
                )
            }
        }

    }

}
