package ru.kiloqky.gb.rickandmortymvp.model.entities.enums

import com.google.gson.annotations.SerializedName

enum class Gender {
    @SerializedName("Female")
    FEMALE,
    @SerializedName("Male")
    MALE,
    @SerializedName("Genderless")
    GENDERLESS,
    @SerializedName("unknown")
    UNKNOWN
}