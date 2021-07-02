package ru.kiloqky.gb.rickandmortymvp.model.repository

import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.entities.*

interface RickAndMortyRepository {

    fun loadCharacters(): Single<CharacterResult>

    fun loadCharacterById(id: Int): Single<Character>

    fun loadNextPageCharacter(url: String): Single<CharacterResult>

    fun loadEpisodes(): Single<EpisodesResult>

    fun loadEpisodeById(id: Int): Single<Episode>

    fun loadNextPageEpisode(url: String): Single<EpisodesResult>

    fun loadLocations(): Single<LocationResult>

    fun loadLocationById(id: Int): Single<Location>

    fun loadLocationByUrl(url: String): Single<LocationResult>
}