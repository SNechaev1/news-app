package men.snechaev.news_app.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class NewsRaw {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null
    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null


    class Article {

        @SerializedName("source")
        @Expose
        var source: Source? = null
        @SerializedName("author")
        @Expose
        var author: Any? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("urlToImage")
        @Expose
        var urlToImage: String? = null
        @SerializedName("publishedAt")
        @Expose
        var publishedAt: String? = null
        @SerializedName("content")
        @Expose
        var content: String? = null

        override fun toString(): String {
            return "author ${author.toString()}"
        }
    }

    class Source {

        @SerializedName("id")
        @Expose
        var id: Any? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
    }

    override fun toString(): String {
        return "status $status , articles ${articles.toString()}"
    }
}
