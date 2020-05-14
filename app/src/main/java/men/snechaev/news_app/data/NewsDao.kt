package men.snechaev.news_app.data

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

    // The Int type parameter tells Room to use a PositionalDataSource
    // object, with position-based loading under the hood.
    @Query("SELECT * FROM news")
    fun getAll(): DataSource.Factory<Int, News>

    @Query("SELECT * FROM news WHERE id = :id")
    fun getById(id: Int): DataSource.Factory<Int, News>

    @Query("SELECT * FROM news WHERE page = :page")
    fun getByPage(page: Int): List<News>

    @Query("SELECT max(page) FROM news")
    fun getLastPage(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<News>)
}