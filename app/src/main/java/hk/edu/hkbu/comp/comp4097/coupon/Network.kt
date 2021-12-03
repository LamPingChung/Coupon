package hk.edu.hkbu.comp.comp4097.coupon

import java.net.HttpURLConnection
import java.net.URL

object Network {

    fun getTextFromNetwork(url: String): String {
        val builder = StringBuilder()
        val connection = URL(url).openConnection() as HttpURLConnection
        var data: Int = connection.inputStream.read()
        while (data != -1) {
            builder.append(data.toChar())
            data = connection.inputStream.read()
        }
        return builder.toString()
    }
}
