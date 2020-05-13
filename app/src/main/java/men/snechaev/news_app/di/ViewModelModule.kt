package men.snechaev.news_app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import men.snechaev.news_app.ui.newslist.NewsViewModel
import men.snechaev.news_app.ui.newslist.NewsViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindUserViewModel(userViewModel: NewsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: NewsViewModelFactory): ViewModelProvider.Factory
}
