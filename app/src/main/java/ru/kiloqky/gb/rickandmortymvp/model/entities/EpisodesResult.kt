package ru.kiloqky.gb.rickandmortymvp.model.entities

import com.google.gson.annotations.SerializedName
import ru.kiloqky.gb.rickandmortymvp.model.general_entites.Info

data class EpisodesResult(
    @SerializedName("results") val episodes: List<Episode>,
    @SerializedName("info") val info: Info
)
