package men.snechaev.news_app.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import men.snechaev.news_app.network.NetStatus
import men.snechaev.news_app.network.NewsRaw
import men.snechaev.news_app.network.WebService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepo @Inject
constructor(
    val webService: WebService,
    val newsDao: NewsDao
) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val _networkErrors = MutableLiveData<Boolean>()

    // LiveData of network errors.
    val networkErrors: LiveData<Boolean>
        get() = _networkErrors

    // keep the last requested page. When the request is successful, increment the page number.
    // not used, because right now page saved in db(not optimal, but easy to use)
    private var lastRequestedPage = 1

    fun requestAndSaveNews(loadSize: Int = 1, startPage: Int = 1) {
        if (!NetStatus.isConnected) {
            Log.d("net", "notConnected")
            return
        }
//        if (job.isActive) return
        scope.launch {
            try {
                // because range inclusive, 5..5 loaded 5; 5..6 loaded 5 and 6,
                // we need decrement endPage by one
                val endPage = startPage + loadSize - 1
                for (pageCount in startPage..endPage) {
                    val response = webService.getNews(page = pageCount)
                    when {
                        response.isSuccessful -> {
                            val articles = response.body()?.articles
                            val newsList =
                                articles?.let { convertNewsRawToNews(page = 1, articles = it) }
                            newsList?.let { insertNewsIntoDb(it) }
                        }
                    }
                }

            } catch (exception: Exception) {
                Log.e("NewsDataSource", exception.message.orEmpty())
            }
        }
    }

    // we need only several field from news article
    fun convertNewsRawToNews(page: Int, articles: List<NewsRaw.Article>): MutableList<News> {
        val newsList: MutableList<News> = mutableListOf()
        for (article in articles) {
//            Log.d("article", article.toString())
            val news = News(
                page = page,
                title = article.title,
                description = article.description,
                urlToImage = article.urlToImage,
                publishedAt = article.publishedAt,
                url = article.url
            )
            newsList.add(news)
        }
        return newsList
    }

    fun insertNewsIntoDb(list: List<News>) {
        newsDao.insertAll(list)
    }

//    fun getNewsFromDb(): List<News> {
//        return newsDao.getAll()
//    }

    fun getNewsByPageFromDb(page: Int): List<News> {
        return newsDao.getByPage(page)
    }
}
