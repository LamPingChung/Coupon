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


data class Malls (val mall: String, val latitude: Double, val longitude: Double)

object MallsViewModel: ViewModel() {
    private val _malls = MutableLiveData(mutableListOf<Malls>())
    var data: LiveData<MutableList<Malls>> = _malls

    init {
        reloadData()
    }

    private fun reloadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val json = "[\n" +
                    "  {\n" +
                    "    \"mall\": \"IFC Mall\",\n" +
                    "    \"latitude\": 22.2849,\n" +
                    "    \"longitude\": 114.158917\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"mall\": \"Pacific Place\",\n" +
                    "    \"latitude\": 22.2774985,\n" +
                    "    \"longitude\": 114.1663225\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"mall\": \"Times Square\",\n" +
                    "    \"latitude\": 22.2782079,\n" +
                    "    \"longitude\": 114.1822994\n" +
                    "  }, \n" +
                    "  {\n" +
                    "    \"mall\": \"Elements\",\n" +
                    "    \"latitude\": 22.3048708,\n" +
                    "    \"longitude\": 114.1615219\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"mall\": \"Harbour City\",\n" +
                    "    \"latitude\": 22.2950689,\n" +
                    "    \"longitude\": 114.1668661\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"mall\": \"Festival Walk\",\n" +
                    "    \"latitude\": 22.3372971,\n" +
                    "    \"longitude\": 114.1745273\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"mall\": \"MegaBox\",\n" +
                    "    \"latitude\": 22.319857,\n" +
                    "    \"longitude\": 114.208168\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"mall\": \"APM\",\n" +
                    "    \"latitude\": 22.3121738,\n" +
                    "    \"longitude\": 114.22513219999996\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"mall\": \"Tsuen Wan Plaza\",\n" +
                    "    \"latitude\": 22.370735,\n" +
                    "    \"longitude\": 114.111309\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"mall\": \"New Town Plaza\",\n" +
                    "    \"latitude\": 22.3814592,\n" +
                    "    \"longitude\": 114.1889307\n" +
                    "  }\n" +
                    "]"
            print(json)
            _malls.postValue(mutableListOf<Malls>().apply {
                addAll(Gson().fromJson<List<Malls>>(json,object : TypeToken<List<Malls>>() {}.type))
            })
        }
    }
}