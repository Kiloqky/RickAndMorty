package ru.kiloqky.gb.rickandmortymvp.model.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "episode_table")
data class Episode(
    @SerializedName("id") @PrimaryKey var id: Int = 0,
    @SerializedName("name") var name: String? = null,
    @SerializedName("air_date") var date: String? = null,
    @Ignore
    @SerializedName("characters") var characters: List<String>? = null,
    @SerializedName("episode") var episode: String? = null
) : Parcelable