package ru.kiloqky.gb.rickandmortymvp.model.entities

import com.google.gson.annotations.SerializedName
import ru.kiloqky.gb.rickandmortymvp.model.general_entites.Info

data class CharacterResult(
    @SerializedName("results") val characters: List<Character>,
    @SerializedName("info") val info: Info
)