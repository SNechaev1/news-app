package men.snechaev.news_app.ui.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import men.snechaev.news_app.data.News
import men.snechaev.news_app.data.NewsDao


class NewsViewModel(newsDao: NewsDao) : ViewModel() {

    val newsList: LiveData<PagedList<News>> =
        newsDao.getAll().toLiveData(pageSize = 50)
}
