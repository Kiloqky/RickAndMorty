package ru.kiloqky.gb.rickandmortymvp.presentation.characters.character

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character

@StateStrategyType(AddToEndStrategy::class)
interface CharacterView : MvpView {
    fun init(character: Character)
    fun onError(t: Throwable)
}