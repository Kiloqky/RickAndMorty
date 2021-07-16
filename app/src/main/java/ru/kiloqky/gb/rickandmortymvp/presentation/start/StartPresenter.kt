package ru.kiloqky.gb.rickandmortymvp.presentation.start

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.kiloqky.gb.rickandmortymvp.IScreens

class StartPresenter(val router: Router, val screens: IScreens) : MvpPresenter<StartView>() {

    fun locationsClicked() {

    }

    fun characterClicked() {
        router.navigateTo(screens.CharactersScreen())
    }

    fun episodesClicked(){

    }
}