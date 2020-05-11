package men.snechaev.news_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var page: Int,
        var title: String?,
        var description: String?,
        var urlToImage: String?,
        var publishedAt: String?,
        var url: String?
        )