package ru.kiloqky.gb.rickandmortymvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import ru.kiloqky.gb.rickandmortymvp.model.entities.*

interface RickAndMortyApi {
    @GET
    fun loadCharacters(@Url url: String = "https://rickandmortyapi.com/api/character"): Single<CharacterResult>

    @GET("character/{id}")
    fun loadCharacterById(@Path("id") id: Int): Single<Character>

    @GET
    fun loadEpisodes(@Url url: String = "https://rickandmortyapi.com/api/episode"): Single<EpisodeResult>

    @GET("episode/{id}")
    fun loadEpisodesById(@Path("id") id: Int): Single<Episode>

    @GET
    fun loadLocations(@Url url: String = "https://rickandmortyapi.com/api/location"): Single<LocationResult>

    @GET("location/{id}")
    fun loadLocationById(@Path("id") id: Int): Single<Location>
}