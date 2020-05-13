package men.snechaev.news_app.ui.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import men.snechaev.news_app.data.News
import men.snechaev.news_app.data.NewsBoundaryCallback
import men.snechaev.news_app.data.NewsDao
import javax.inject.Inject


class NewsViewModel @Inject constructor(
    newsDao: NewsDao,
    boundaryCallback: NewsBoundaryCallback
) : ViewModel() {

    val newsList: LiveData<PagedList<News>> =
        newsDao.getAll().toLiveData(pageSize = 1, boundaryCallback = boundaryCallback)

}
