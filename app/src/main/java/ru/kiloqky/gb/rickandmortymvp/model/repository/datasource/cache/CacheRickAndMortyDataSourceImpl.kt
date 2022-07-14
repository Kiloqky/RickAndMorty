package ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.entities.Episode
import ru.kiloqky.gb.rickandmortymvp.model.entities.Location
import ru.kiloqky.gb.rickandmortymvp.model.storage.CharacterStorage
import ru.kiloqky.gb.rickandmortymvp.model.storage.EpisodeStorage
import ru.kiloqky.gb.rickandmortymvp.model.storage.LocationStorage

class CacheRickAndMortyDataSourceImpl(
    private val characterStorage: CharacterStorage,
    private val locationStorage: LocationStorage,
    private val episodeStorage: EpisodeStorage
) : CacheRickAndMortyDataSource {

    override fun loadCharacters(): Single<List<Character>> =
        characterStorage
            .characterDao()
            .load()

    override fun retainCharacters(characters: List<Character>): Completable =
        characterStorage
            .characterDao()
            .retain(characters)

    override fun loadLocations(): Single<List<Location>> =
        locationStorage
            .locationDao()
            .load()

    override fun retainLocations(locations: List<Location>): Completable =
        locationStorage
            .locationDao()
            .retain(locations)

    override fun loadEpisodes(): Single<List<Episode>> =
        episodeStorage
            .episodeDao()
            .load()

    override fun retainEpisodes(episodes: List<Episode>): Completable =
        episodeStorage
            .episodeDao()
            .retain(episodes)
}