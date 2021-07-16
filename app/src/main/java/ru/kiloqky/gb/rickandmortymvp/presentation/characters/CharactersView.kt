package ru.kiloqky.gb.rickandmortymvp.presentation.characters

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface CharactersView: MvpView {
    fun init()
    fun updateList(startSize: Int, endSize: Int)
    fun showProgress()
    fun hideProgress()
    fun reloadList()
    fun showError(t: Throwable)
}