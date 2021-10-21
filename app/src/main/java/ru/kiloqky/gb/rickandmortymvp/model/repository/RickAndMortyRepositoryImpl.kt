package ru.kiloqky.gb.rickandmortymvp.model.repository

import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.entities.*
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.RickAndMortyDataSource
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.cache.CacheRickAndMortyDataSource

class RickAndMortyRepositoryImpl(
    private val cloudDataSource: RickAndMortyDataSource,
    private val cacheDataSource: CacheRickAndMortyDataSource
) : RickAndMortyRepository {
    override fun loadCharacters(): Single<CharacterResult> =
        cloudDataSource.loadCharacters()
            .flatMap(::retainCharactersToCache)

    override fun loadCharacterById(id: Int): Single<Character> =
        cloudDataSource.loadCharacterById(id)

    override fun loadCharactersByUrl(url: String): Single<CharacterResult> =
        cloudDataSource.loadCharactersByUrl(url)
            .flatMap(::retainCharactersToCache)

    override fun loadEpisodes(): Single<EpisodeResult> =
        cloudDataSource.loadEpisodes()
            .doAfterSuccess(::retainEpisodesToCache)
            .doOnError(::takeEpisodesCacheData)

    override fun loadEpisodeById(id: Int): Single<Episode> =
        cloudDataSource.loadEpisodeById(id)

    override fun loadEpisodesByUrl(url: String): Single<EpisodeResult> =
        cloudDataSource.loadEpisodesByUrl(url)
            .doAfterSuccess(::retainEpisodesToCache)

    override fun loadLocations(): Single<LocationResult> =
        cloudDataSource.loadLocation()
            .flatMap (::retainLocationsToCache)
            .doOnError(::takeLocationsCacheData)

    override fun loadLocationById(id: Int): Single<Location> =
        cloudDataSource.loadLocationById(id)

    override fun loadLocationByUrl(url: String): Single<LocationResult> =
        cloudDataSource.loadLocationByUrl(url)
            .doAfterSuccess(::retainLocationsToCache)

    private fun retainCharactersToCache(result: CharacterResult): Single<CharacterResult> {
        cacheDataSource.retainCharacters(result.characters)
        return Single.just(result)
    }

    private fun retainLocationsToCache(result: LocationResult): Single<LocationResult> {
        cacheDataSource.retainLocations(result.locations)
        return Single.just(result)
    }

    private fun retainEpisodesToCache(result: EpisodeResult):Single<EpisodeResult> {
        cacheDataSource.retainEpisodes(result.episodes)
        return Single.just(result)
    }

    private fun takeCharactersCacheData(t: Throwable): Single<CharacterResult> =
        cacheDataSource
            .loadCharacters()
            .map(ResultMapper::characterMap)

    private fun takeLocationsCacheData(t: Throwable): Single<LocationResult> =
        cacheDataSource
            .loadLocations()
            .map(ResultMapper::locationMap)

    private fun takeEpisodesCacheData(t: Throwable): Single<EpisodeResult> =
        cacheDataSource
            .loadEpisodes()
            .map(ResultMapper::episodesMap)

    override fun searchCharactersByName(name: String): Single<CharacterResult> =
        cloudDataSource.loadCharactersByUrl(provideSearchNameLink(name))

    private fun provideSearchNameLink(name: String): String =
        "https://rickandmortyapi.com/api/character/?name=$name"

}