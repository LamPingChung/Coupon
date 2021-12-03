package hk.edu.hkbu.comp.comp4097.coupon.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hk.edu.hkbu.comp.comp4097.coupon.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Coupons (val id: Int, val title: String, val quota: Int, val coin: Int, val restaurant: String, val expirydate: String, val region: String, val mall: String, val image: String, val detail: String)

object CouponsViewModel: ViewModel() {
    private val _coupons = MutableLiveData(mutableListOf<Coupons>())
    var data: LiveData<MutableList<Coupons>> = _coupons

    init {
        reloadData()
    }

    private fun reloadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val json = Network.getTextFromNetwork("https://comp4097-2021ass1.herokuapp.com")
            print(json)
            _coupons.postValue(mutableListOf<Coupons>().apply {
                addAll(Gson().fromJson<List<Coupons>>(json,object : TypeToken<List<Coupons>>() {}.type))
            })
        }
    }
}