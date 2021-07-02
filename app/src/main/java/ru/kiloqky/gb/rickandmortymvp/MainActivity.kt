package ru.kiloqky.gb.rickandmortymvp

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import ru.kiloqky.gb.rickandmortymvp.presentation.abs.AbsActivity
import javax.inject.Inject

class MainActivity : AbsActivity(R.layout.activity_main) {
    private val navigator = MainAppNavigator(this, R.id.fragment_container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RickAndMortyApp)
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(screens.StartScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}