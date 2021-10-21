package ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.cloud

import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.api.RickAndMortyApi
import ru.kiloqky.gb.rickandmortymvp.model.entities.*
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.RickAndMortyDataSource

class CloudRickAndMortyDataSource(private val api: RickAndMortyApi) : RickAndMortyDataSource {
    override fun loadCharacters(): Single<CharacterResult> =
        api.loadCharacters()

    override fun loadCharacterById(id: Int): Single<Character> =
        api.loadCharacterById(id)

    override fun loadCharactersByUrl(url: String): Single<CharacterResult> =
        api.loadCharacters(url)

    override fun loadLocation(): Single<LocationResult> =
        api.loadLocations()

    override fun loadLocationById(id: Int): Single<Location> =
        api.loadLocationById(id)

    override fun loadLocationByUrl(url: String): Single<LocationResult> =
        api.loadLocations(url)

    override fun loadEpisodes(): Single<EpisodeResult> =
        api.loadEpisodes()

    override fun loadEpisodeById(id: Int): Single<Episode> =
        api.loadEpisodesById(id)

    override fun loadEpisodesByUrl(url: String): Single<EpisodeResult> =
        api.loadEpisodes(url)
}