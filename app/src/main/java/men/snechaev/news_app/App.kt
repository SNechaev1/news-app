package men.snechaev.news_app

import android.app.Application
import android.content.Context
import androidx.annotation.VisibleForTesting
import men.snechaev.news_app.di.AppComponent
import men.snechaev.news_app.di.DaggerAppComponent


class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.factory().create(this)
    }

    companion object {
        private var INSTANCE: App? = null
        @JvmStatic
        fun get(): App = INSTANCE!!
    }

    @VisibleForTesting
    fun setComponent(appComponent: DaggerAppComponent) {
        this.appComponent = appComponent
    }

}