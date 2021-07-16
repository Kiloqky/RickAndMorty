package ru.kiloqky.gb.rickandmortymvp.presentation.start

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.rickandmortymvp.IScreens
import ru.kiloqky.gb.rickandmortymvp.R
import ru.kiloqky.gb.rickandmortymvp.databinding.StartFragmentBinding
import ru.kiloqky.gb.rickandmortymvp.helpers.click
import ru.kiloqky.gb.rickandmortymvp.presentation.abs.AbsFragment
import javax.inject.Inject

@SuppressLint("unused")
class StartFragment : AbsFragment(R.layout.start_fragment), StartView {

    private val binding: StartFragmentBinding by viewBinding()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    private val presenter: StartPresenter by moxyPresenter {
        StartPresenter(router, screens)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characterContainer.click(presenter::characterClicked)
        binding.locationContainer.click(presenter::locationsClicked)
        binding.episodesContainer.click(presenter::episodesClicked)
    }

    companion object {
        fun newInstance() = StartFragment()
    }
}