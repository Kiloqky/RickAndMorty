package ru.kiloqky.gb.rickandmortymvp

import androidx.appcompat.app.AppCompatDelegate
import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.kiloqky.gb.rickandmortymvp.scheduler.DefaultSchedulers
import ru.kiloqky.gb.rickandmortymvp.di.AppComponent
import ru.kiloqky.gb.rickandmortymvp.di.DaggerAppComponent

class App: DaggerApplication() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()

                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
                withScreens(ScreensImpl())
            }
            .withSchedulers(DefaultSchedulers())
            .build()

}