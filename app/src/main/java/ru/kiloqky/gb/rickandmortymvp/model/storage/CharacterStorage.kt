package ru.kiloqky.gb.rickandmortymvp.model.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.storage.converters.GenderConverter
import ru.kiloqky.gb.rickandmortymvp.model.storage.converters.StatusConverter
import ru.kiloqky.gb.rickandmortymvp.model.storage.dao.CharacterDao

@Database(version = 1, entities = [Character::class], exportSchema = false)
@TypeConverters(StatusConverter::class, GenderConverter::class)
abstract class CharacterStorage : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}