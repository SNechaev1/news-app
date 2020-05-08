package men.snechaev.news_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import men.snechaev.news_app.ui.dummy.DummyContent
import men.snechaev.news_app.ui.newslist.NewsFragment

class MainActivity : AppCompatActivity(), NewsFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
