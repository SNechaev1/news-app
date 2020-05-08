package men.snechaev.news_app.network

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("v2/everything?q=android&from=2019-04-00&sortBy=publishedAt&apiKey=26eddb253e7840f988aec61f2ece2907&page={page}")
    suspend fun getNews(@Path("page") id: Long): NewsJson

}
