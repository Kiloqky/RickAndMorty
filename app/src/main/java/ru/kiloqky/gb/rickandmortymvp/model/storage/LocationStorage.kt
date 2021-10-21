package ru.kiloqky.gb.rickandmortymvp.model.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.entities.Location
import ru.kiloqky.gb.rickandmortymvp.model.storage.dao.EpisodeDao
import ru.kiloqky.gb.rickandmortymvp.model.storage.dao.LocationDao

@Database(version = 1, entities = [Location::class], exportSchema = false)
abstract class LocationStorage: RoomDatabase() {
    abstract fun locationDao(): LocationDao
}