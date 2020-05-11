package men.snechaev.news_app.network

import men.snechaev.news_app.data.NewsRaw
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") q: String = "android",
        @Query("from") from: String = "2019-04-00",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "26eddb253e7840f988aec61f2ece2907",
        @Query("page") page: Int
    ): NewsRaw

}
