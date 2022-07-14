package ru.kiloqky.gb.rickandmortymvp.model.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.kiloqky.gb.rickandmortymvp.model.entities.enums.Gender
import ru.kiloqky.gb.rickandmortymvp.model.entities.enums.Status

@Parcelize
@Entity(tableName = "character_table")
data class Character(
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("species")
    var species: String? = null,
    @SerializedName("status")
    var status: Status? = null,
    @SerializedName("gender")
    var gender: Gender? = null,
    @SerializedName("image")
    var imageUrl: String? = null,
    @Ignore
    @SerializedName("location")
    var location: Location? = null,
    @Ignore
    @SerializedName("episode")
    var episodesUrl: List<String>? = null
) : Parcelable