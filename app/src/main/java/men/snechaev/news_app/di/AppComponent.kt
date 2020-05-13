package men.snechaev.news_app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import men.snechaev.news_app.ui.newslist.NewsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
        AppModule::class,
        ViewModelModule::class,
        NetModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(fragment: NewsFragment)

}