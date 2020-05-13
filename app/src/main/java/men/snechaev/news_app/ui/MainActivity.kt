package men.snechaev.news_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import men.snechaev.news_app.R
import men.snechaev.news_app.data.News
import men.snechaev.news_app.ui.newslist.NewsFragment

class MainActivity : AppCompatActivity(), NewsFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onListFragmentInteraction(item: News?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
