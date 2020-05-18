package men.snechaev.news_app.ui.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import men.snechaev.news_app.App
import men.snechaev.news_app.R
import javax.inject.Inject


class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModel: NewsViewModel

//    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.get().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = NewsAdapter()
//            view.adapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
            viewModel.newsList.observe(
                viewLifecycleOwner,
                Observer { pagedList -> (view.adapter as NewsAdapter).submitList(pagedList) })

        }
        return view
    }

}
