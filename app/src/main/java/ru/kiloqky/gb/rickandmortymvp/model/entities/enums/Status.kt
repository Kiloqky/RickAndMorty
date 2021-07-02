package ru.kiloqky.gb.rickandmortyapp.model.character.entities.enums

import com.google.gson.annotations.SerializedName

enum class Status {
    @SerializedName("Alive")
    ALIVE,
    @SerializedName("Dead")
    DEAD,
    @SerializedName("unknown")
    UNKNOWN
}