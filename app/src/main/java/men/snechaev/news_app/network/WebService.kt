package men.snechaev.news_app.network

import men.snechaev.news_app.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") q: String = "android",
        @Query("from") from: String = "2019-04-00",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): Response<NewsRaw>

}
