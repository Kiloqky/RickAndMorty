package ru.kiloqky.gb.rickandmortymvp.model.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Episode(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("air_date") val date: String,
    @SerializedName("characters") val characters: List<String>,
    @SerializedName("episode") val episode: String
) : Parcelable