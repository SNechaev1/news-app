package men.snechaev.news_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import men.snechaev.news_app.data.News
import men.snechaev.news_app.data.NewsDao

@Database(
    entities = [News::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDb : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object{
        val DATABASE_NAME: String = "news_db"
    }
}
