package men.snechaev.news_app.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import men.snechaev.news_app.network.WebService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepo @Inject
constructor(
    val webService: WebService,
    val newsDao: NewsDao
) {


    fun requestNews(page: Int) {
        runBlocking {
            withContext(Dispatchers.IO) {
                val newsRaw = webService.getNews(page = page)
                Log.d("net", newsRaw.toString())
                convertNewsRawToNews(page, newsRaw = newsRaw)
            }
        }
    }

    // we need only several field from news article
    fun convertNewsRawToNews(page: Int, newsRaw: NewsRaw): List<News> {
        val newsList: MutableList<News> = mutableListOf()
        for(article in newsRaw.articles!!) {
//            Log.d("article", article.toString())
            val news: News = News(page = page, title = article.title, description = article.description,
                urlToImage = article.urlToImage, publishedAt = article.publishedAt)
            newsList.add(news)
        }
        insertNewsIntoDb(newsList)
        Log.d("db", getNewsByPageFromDb(1).toString())
        return newsList
    }

    fun insertNewsIntoDb(list: List<News>) {
        newsDao.insertAll(list)
    }

    fun getNewsFromDb(): List<News> {
        return newsDao.getAll()
    }

    fun getNewsByPageFromDb(page: Int): List<News> {
        return newsDao.getByPage(page)
    }
}
