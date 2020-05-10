package men.snechaev.news_app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import men.snechaev.news_app.data.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<News>

    @Query("SELECT * FROM news WHERE page = :page")
    fun getByPage(page: Int): List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<News>)
}