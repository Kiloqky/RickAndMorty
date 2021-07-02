package ru.kiloqky.gb.rickandmortymvp.presentation.characters.adapter

import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.presentation.rvinterfaces.ItemView

interface CharacterItemView: ItemView {
    fun setInfo(character: Character)
}