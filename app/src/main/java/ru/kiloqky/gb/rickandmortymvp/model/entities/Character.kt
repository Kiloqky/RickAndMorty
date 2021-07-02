package ru.kiloqky.gb.rickandmortymvp.model.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.kiloqky.gb.rickandmortyapp.model.character.entities.enums.Gender
import ru.kiloqky.gb.rickandmortyapp.model.character.entities.enums.Status

@Parcelize
data class Character(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: Status,
    @SerializedName("gender")
    val gender: Gender,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("episode")
    val episodesUrl: List<String>
) : Parcelable