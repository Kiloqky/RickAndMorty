package ru.kiloqky.gb.rickandmortymvp.model.storage.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.entities.CharacterResult

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character_table")
    fun load(): Single<List<Character>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(character: List<Character>): Completable
}