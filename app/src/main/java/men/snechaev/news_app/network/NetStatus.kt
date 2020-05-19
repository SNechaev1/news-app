package men.snechaev.news_app.network

import android.content.Context
import android.net.ConnectivityManager
import men.snechaev.news_app.App

class NetStatus {

    companion object {
        private val appContext = App.appContext()
        val isConnected: Boolean
            get() {
                val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = connectivityManager.activeNetworkInfo
                return netInfo != null && netInfo.isConnectedOrConnecting
            }
    }

//    val isConnected: Boolean?
//        get() {
//            val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val netInfo = connectivityManager.activeNetworkInfo
//            return netInfo != null && netInfo.isConnectedOrConnecting
//        }
}