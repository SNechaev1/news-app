package men.snechaev.news_app.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import men.snechaev.news_app.network.NewsRaw
import men.snechaev.news_app.network.WebService
import javax.inject.Inject

/**
 * This boundary callback gets notified when user reaches to the edges of the list such that the
 * database cannot provide any more data.
 */
class NewsBoundaryCallback @Inject
constructor(
    val webService: WebService,
    val newsDao: NewsDao
) : PagedList.BoundaryCallback<News>() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    // keep the last requested page. When the request is successful, increment the page number.
    // not used, because right now page saved in db(not optimal, but easy to use)
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false


    // Database returned 0 items. We should query the backend for more items.
    override fun onZeroItemsLoaded() {
        Log.d("RepoBoundaryCallback", "onZeroItemsLoaded")
        requestAndSaveNews(loadSize = 5)
    }


     // When all items in the database were loaded, we need to query the backend for more items.
    override fun onItemAtEndLoaded(itemAtEnd: News) {
         Log.d("RepoBoundaryCallback", "onItemAtEndLoaded")

         requestAndSaveNews(loadSize = 1, startPage = itemAtEnd.page + 1)
    }


    fun requestAndSaveNews(loadSize: Int = 1, startPage: Int = 1 ) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        scope.launch {
            try {
                // because range inclusive, 5..5 loaded 5; 5..6 loaded 5 and 6,
                // we need decrement loadSize by one
                val endPage = startPage + loadSize - 1
                for (pageCount in startPage..endPage) {
                    val response = webService.getNews(page = pageCount)
                    when {
                        response.isSuccessful -> {
                            val articles = response.body()?.articles
                            val newsList = articles?.let { convertNewsRawToNews(page = 1, articles = it) }
                            newsList?.let { insertNewsIntoDb(it) }
                            isRequestInProgress = false
                        }
                    }
                }

            } catch (exception : Exception){
                Log.e("NewsDataSource", exception.message.orEmpty())
                isRequestInProgress = false
            }

        }
    }

    // we need only several field from news article
    fun convertNewsRawToNews(page: Int, articles: List<NewsRaw.Article>): MutableList<News> {
        val newsList: MutableList<News> = mutableListOf()
        for(article in articles) {
//            Log.d("article", article.toString())
            val news = News(page = page, title = article.title, description = article.description,
                urlToImage = article.urlToImage, publishedAt = article.publishedAt, url = article.url)
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