package ru.kiloqky.gb.rickandmortymvp

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface IScreens {
    fun StartScreen(): FragmentScreen

    fun CharacterScreen(id: Int): FragmentScreen

    fun CharactersScreen(): FragmentScreen

    fun LocationScreen(): FragmentScreen

    fun LocationsScreen(): FragmentScreen

    fun EpisodeScreen():FragmentScreen

    fun EpisodesScreen(): FragmentScreen

    fun SearchCharactersFragment(): FragmentScreen
}