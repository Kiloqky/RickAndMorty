package ru.kiloqky.gb.rickandmortymvp

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.CharactersFragment
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.character.CharacterFragment
import ru.kiloqky.gb.rickandmortymvp.presentation.start.StartFragment

class ScreensImpl : IScreens {
    override fun StartScreen() = FragmentScreen {
        StartFragment.newInstance()
    }

    override fun CharacterScreen(id: Int) = FragmentScreen {
        CharacterFragment.newInstance(id)
    }

    override fun CharactersScreen() = FragmentScreen {
        CharactersFragment.newInstance()
    }

    override fun LocationScreen(): FragmentScreen {
        TODO("Not yet implemented")
    }

    override fun LocationsScreen(): FragmentScreen {
        TODO("Not yet implemented")
    }

    override fun EpisodeScreen(): FragmentScreen {
        TODO("Not yet implemented")
    }

    override fun EpisodesScreen(): FragmentScreen {
        TODO("Not yet implemented")
    }


}