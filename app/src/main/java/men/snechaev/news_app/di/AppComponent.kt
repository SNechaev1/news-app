package men.snechaev.news_app.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import men.snechaev.news_app.App
import men.snechaev.news_app.ui.MainActivity
import men.snechaev.news_app.ui.splash.SplashFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
        AppModule::class,
        NetModule::class])
interface AppComponent {

//    val app: Application

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
//        fun create(@BindsInstance context: Context): AppComponent
//        fun create(): AppComponent
    }
    fun inject(activity: MainActivity)
    fun inject(fragment: SplashFragment)
//    fun webService(): WebService
//    fun newsDao(): NewsDao


}