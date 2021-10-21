package ru.kiloqky.gb.rickandmortymvp.model.repository

import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.entities.*

interface RickAndMortyRepository {

    fun loadCharacters(): Single<CharacterResult>

    fun loadCharacterById(id: Int): Single<Character>

    fun loadCharactersByUrl(url: String): Single<CharacterResult>

    fun loadEpisodes(): Single<EpisodeResult>

    fun loadEpisodeById(id: Int): Single<Episode>

    fun loadEpisodesByUrl(url: String): Single<EpisodeResult>

    fun loadLocations(): Single<LocationResult>

    fun loadLocationById(id: Int): Single<Location>

    fun loadLocationByUrl(url: String): Single<LocationResult>

    fun searchCharactersByName(name: String): Single<CharacterResult>
}