package ru.kiloqky.gb.rickandmortymvp.model.repository

import ru.kiloqky.gb.rickandmortymvp.model.entities.*
import ru.kiloqky.gb.rickandmortymvp.model.general_entites.Info

object ResultMapper {
    fun characterMap(characters: List<Character>): CharacterResult =
        CharacterResult(characters, Info(0, 0, null, null))

    fun episodesMap(episodes: List<Episode>): EpisodeResult =
        EpisodeResult(episodes, Info(0, 0, null, null))

    fun locationMap(locations: List<Location>): LocationResult =
        LocationResult(locations, Info(0, 0, null, null))
}