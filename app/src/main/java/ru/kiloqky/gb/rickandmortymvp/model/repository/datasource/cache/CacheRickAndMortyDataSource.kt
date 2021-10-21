package ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.entities.Episode
import ru.kiloqky.gb.rickandmortymvp.model.entities.Location
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.RickAndMortyDataSource

interface CacheRickAndMortyDataSource {

    fun loadCharacters(): Single<List<Character>>

    fun retainCharacters(characters: List<Character>): Completable

    fun loadLocations(): Single<List<Location>>

    fun retainLocations(locations: List<Location>): Completable

    fun loadEpisodes(): Single<List<Episode>>

    fun retainEpisodes(episodes: List<Episode>): Completable
}