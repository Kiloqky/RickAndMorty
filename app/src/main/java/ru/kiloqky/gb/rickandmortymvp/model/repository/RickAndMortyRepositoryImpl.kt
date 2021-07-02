package ru.kiloqky.gb.rickandmortymvp.model.repository

import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.entities.*
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.RickAndMortyDataSource

class RickAndMortyRepositoryImpl(private val cloudDataSource: RickAndMortyDataSource) : RickAndMortyRepository {
    override fun loadCharacters(): Single<CharacterResult> =
        cloudDataSource.loadCharacters()

    override fun loadCharacterById(id: Int): Single<Character> =
        cloudDataSource.loadCharacterById(id)

    override fun loadNextPageCharacter(url: String): Single<CharacterResult> =
        cloudDataSource.loadCharactersByUrl(url)

    override fun loadEpisodes(): Single<EpisodesResult> =
        cloudDataSource.loadEpisodes()

    override fun loadEpisodeById(id: Int): Single<Episode> =
        cloudDataSource.loadEpisodeById(id)

    override fun loadNextPageEpisode(url: String): Single<EpisodesResult> =
        cloudDataSource.loadEpisodesByUrl(url)

    override fun loadLocations(): Single<LocationResult> =
        cloudDataSource.loadLocation()

    override fun loadLocationById(id: Int): Single<Location> =
        cloudDataSource.loadLocationById(id)

    override fun loadLocationByUrl(url: String): Single<LocationResult> =
        cloudDataSource.loadLocationByUrl(url)
}