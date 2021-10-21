package ru.kiloqky.gb.rickandmortymvp.model.storage.dao
import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.entities.Location

@Dao
interface LocationDao {

    @Query("SELECT * FROM location_table")
    fun load(): Single<List<Location>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(location: List<Location>): Completable
}