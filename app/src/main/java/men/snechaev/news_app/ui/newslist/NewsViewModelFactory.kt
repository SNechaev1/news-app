package men.snechaev.news_app.ui.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import men.snechaev.news_app.data.NewsBoundaryCallback
import men.snechaev.news_app.data.NewsDao
import javax.inject.Inject


class NewsViewModelFactory @Inject constructor(
    private val newsDao: NewsDao,
    private val boundaryCallback: NewsBoundaryCallback
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != NewsViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return NewsViewModel(
            newsDao,
            boundaryCallback
        ) as T
    }
}
