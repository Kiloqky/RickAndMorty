package ru.kiloqky.gb.rickandmortymvp.model.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String?,
    @SerializedName("dimension")
    val dimension: String?,
    @SerializedName("url")
    val url: String
) : Parcelable