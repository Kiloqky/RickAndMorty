package ru.kiloqky.gb.rickandmortymvp.model.storage.converters

import androidx.room.TypeConverter
import ru.kiloqky.gb.rickandmortymvp.model.entities.enums.Gender

class GenderConverter {
    @TypeConverter
    fun fromGenderEnumToString(gender: Gender) = gender.name

    @TypeConverter
    fun fromGenderStringToEnum(gender: String) =
        Gender.values().first { it.name == gender }
}