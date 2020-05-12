package men.snechaev.news_app.ui.newslist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import men.snechaev.news_app.R
import men.snechaev.news_app.data.News


class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val viewTitle: TextView = view.findViewById(R.id.news_title)
    private val viewDescription: TextView = view.findViewById(R.id.news_description)
    private val viewDate: TextView = view.findViewById(R.id.news_date)
    private val viewImg: ImageView = view.findViewById(R.id.news_img)

    private var news: News? = null

    init {
        view.setOnClickListener {
            news?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(news: News?) {
        if (news == null) {
            val resources = itemView.resources
            viewTitle.text = resources.getString(R.string.loading)
            viewDescription.visibility = View.GONE
            viewImg.visibility = View.GONE
            viewDate.visibility = View.GONE
        } else {
            showNewsData(news)
        }
    }

    private fun showNewsData(news: News) {
//        this.news = news
        with(news) {
            viewTitle.text = title
            viewDescription.text = description
            viewDate.text = publishedAt
            Picasso.get().load(urlToImage).into(viewImg)
        }


    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.news_view_item, parent, false)
            return NewsViewHolder(view)
        }
    }
}
