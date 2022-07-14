package ru.kiloqky.gb.rickandmortymvp.model.entities.enums

import com.google.gson.annotations.SerializedName

enum class Status {
    @SerializedName("Alive")
    ALIVE,
    @SerializedName("Dead")
    DEAD,
    @SerializedName("unknown")
    UNKNOWN
}