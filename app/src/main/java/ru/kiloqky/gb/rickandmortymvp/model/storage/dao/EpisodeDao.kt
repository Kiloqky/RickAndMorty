package ru.kiloqky.gb.rickandmortymvp.model.storage.dao

import android.location.Location
import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.entities.Episode

@Dao
interface EpisodeDao {
    @Query("SELECT * FROM episode_table")
    fun load(): Single<List<Episode>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(episode: List<Episode>): Completable
}