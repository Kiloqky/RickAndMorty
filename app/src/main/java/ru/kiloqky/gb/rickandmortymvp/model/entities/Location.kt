package ru.kiloqky.gb.rickandmortymvp.model.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "location_table")
data class Location(
    @SerializedName("id")
    @PrimaryKey
    val id: Int = 0,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String?,
    @SerializedName("dimension")
    val dimension: String?,
    @SerializedName("url")
    val url: String
) : Parcelable