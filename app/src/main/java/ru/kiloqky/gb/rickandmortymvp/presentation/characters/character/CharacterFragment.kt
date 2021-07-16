package ru.kiloqky.gb.rickandmortymvp.presentation.characters.character

import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.rickandmortymvp.IScreens
import ru.kiloqky.gb.rickandmortymvp.R
import ru.kiloqky.gb.rickandmortymvp.databinding.FragmentCharacterBinding
import ru.kiloqky.gb.rickandmortymvp.helpers.arguments
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.imageloader.GlideImageLoader
import ru.kiloqky.gb.rickandmortymvp.model.repository.RickAndMortyRepository
import ru.kiloqky.gb.rickandmortymvp.presentation.abs.AbsFragment
import ru.kiloqky.gb.rickandmortymvp.scheduler.Schedulers
import javax.inject.Inject

class CharacterFragment : AbsFragment(R.layout.fragment_character), CharacterView {

    private val binding: FragmentCharacterBinding by viewBinding()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var repository: RickAndMortyRepository

    private val presenter: CharacterPresenter by moxyPresenter {
        CharacterPresenter(characterId, router, screens, schedulers, repository)
    }

    private val characterId: Int by lazy {
        arguments?.getInt(ARGS_CHARACTER_ID)
            ?: presenter.unknownId()
    }

    private val imageLoader: GlideImageLoader by lazy {
        GlideImageLoader()
    }

    companion object {
        const val ARGS_CHARACTER_ID = "character_id"
        fun newInstance(characterId: Int) = CharacterFragment()
            .arguments(ARGS_CHARACTER_ID to characterId)
    }

    override fun init(character: Character) {
        binding.gender.text = character.gender.name
        binding.location.text = character.location.name
        binding.status.text = character.status.name
        binding.name.text = character.name
        binding.species.text = character.species
        imageLoader.loadInto(
            character.imageUrl,
            binding.avatar
        )
    }


    override fun onError(t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }

}