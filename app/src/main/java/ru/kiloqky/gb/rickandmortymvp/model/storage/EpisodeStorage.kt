package ru.kiloqky.gb.rickandmortymvp.model.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.entities.Episode
import ru.kiloqky.gb.rickandmortymvp.model.storage.dao.EpisodeDao

@Database(version = 1, entities = [Episode::class], exportSchema = false)
abstract class EpisodeStorage: RoomDatabase() {
    abstract fun episodeDao(): EpisodeDao
}