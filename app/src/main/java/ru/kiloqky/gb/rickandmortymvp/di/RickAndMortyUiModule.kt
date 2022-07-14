package ru.kiloqky.gb.rickandmortymvp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.kiloqky.gb.rickandmortymvp.MainActivity
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.CharactersFragment
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.character.CharacterFragment
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.characters_search.SearchCharactersFragment
import ru.kiloqky.gb.rickandmortymvp.presentation.start.StartFragment

@Module
abstract class RickAndMortyUiModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun bindCharacterFragment(): CharacterFragment

    @ContributesAndroidInjector
    abstract fun bindCharactersFragment(): CharactersFragment

    @ContributesAndroidInjector
    abstract fun bindSearchCharactersFragment(): SearchCharactersFragment
}