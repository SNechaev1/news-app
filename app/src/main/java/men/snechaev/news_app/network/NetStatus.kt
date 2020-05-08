package men.snechaev.news_app.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetStatus(private var applicationContext: Context) {

    val isConnected: Boolean?
        get() {
            val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting

        }


}