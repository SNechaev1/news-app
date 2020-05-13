package men.snechaev.news_app.data

import android.util.Log
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

    // we need only several field from news article
    fun convertNewsRawToNews(page: Int, newsRaw: NewsRaw): List<News> {
        val newsList: MutableList<News> = mutableListOf()
        for(article in newsRaw.articles!!) {
//            Log.d("article", article.toString())
            val news: News = News(page = page, title = article.title, description = article.description,
                urlToImage = article.urlToImage, publishedAt = article.publishedAt, url = article.url)
            newsList.add(news)
        }
        insertNewsIntoDb(newsList)
        Log.d("db", getNewsByPageFromDb(1).toString())
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
