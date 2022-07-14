package ru.kiloqky.gb.rickandmortymvp.model.storage.converters

import androidx.room.TypeConverter
import ru.kiloqky.gb.rickandmortymvp.model.entities.enums.Gender
import ru.kiloqky.gb.rickandmortymvp.model.entities.enums.Status

class StatusConverter {
    @TypeConverter
    fun fromEnumToString(status: Status) = status.name

    @TypeConverter
    fun fromStringToEnum(status: String) =
        Status.values().first { it.name == status }
}