package ru.kiloqky.gb.rickandmortymvp.model.entities

import com.google.gson.annotations.SerializedName
import ru.kiloqky.gb.rickandmortymvp.model.general_entites.Info

data class LocationResult (
    @SerializedName("results") val locations: List<Location>,
    @SerializedName("info") val info: Info
)