package men.snechaev.news_app.di


import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import men.snechaev.news_app.data.NewsDao
import men.snechaev.news_app.data.NewsDb
import men.snechaev.news_app.data.NewsDb.Companion.DATABASE_NAME
import javax.inject.Singleton

@Module
object AppModule{

    @JvmStatic
    @Singleton
    @Provides
    fun provideNewsDb(context: Context): NewsDb {
        return Room
            .databaseBuilder(context, NewsDb::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDb): NewsDao {
        return db.getNewsDao()
    }

}