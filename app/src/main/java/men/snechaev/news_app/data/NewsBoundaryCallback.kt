package men.snechaev.news_app.data

import android.util.Log
import androidx.paging.PagedList
import javax.inject.Inject

/**
 * This boundary callback gets notified when user reaches to the edges of the list such that the
 * database cannot provide any more data.
 */
class NewsBoundaryCallback @Inject
constructor(
    val dataRepo: DataRepo
) : PagedList.BoundaryCallback<News>() {

    // Database returned 0 items. We should query the backend for more items.
    override fun onZeroItemsLoaded() {
        Log.d("RepoBoundaryCallback", "onZeroItemsLoaded")
        dataRepo.requestAndSaveNews(loadSize = 1)
    }

    // When all items in the database were loaded, we need to query the backend for more items.
    override fun onItemAtEndLoaded(itemAtEnd: News) {
        Log.d("RepoBoundaryCallback", "onItemAtEndLoaded")
        dataRepo.requestAndSaveNews(loadSize = 1, startPage = itemAtEnd.page + 1)
    }

}