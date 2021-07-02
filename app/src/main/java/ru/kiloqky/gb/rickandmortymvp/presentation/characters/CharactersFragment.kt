package ru.kiloqky.gb.rickandmortymvp.presentation.characters

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.rickandmortymvp.IScreens
import ru.kiloqky.gb.rickandmortymvp.R
import ru.kiloqky.gb.rickandmortymvp.databinding.FragmentCharactersBinding
import ru.kiloqky.gb.rickandmortymvp.model.repository.RickAndMortyRepository
import ru.kiloqky.gb.rickandmortymvp.model.imageloader.GlideImageLoader
import ru.kiloqky.gb.rickandmortymvp.presentation.abs.AbsFragment
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.adapter.CharactersRVAdapter
import ru.kiloqky.gb.rickandmortymvp.scheduler.Schedulers
import javax.inject.Inject

class CharactersFragment : AbsFragment(R.layout.fragment_characters), CharactersView {

    private val binding: FragmentCharactersBinding by viewBinding()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var repository: RickAndMortyRepository

    private val presenter: CharactersPresenter by moxyPresenter {
        CharactersPresenter(router, screens, schedulers, repository)
    }

    private var characterAdapter: CharactersRVAdapter? = null


    override fun updateList(startSize: Int, endSize: Int) {
        characterAdapter?.notifyItemRangeInserted(startSize, endSize)
    }

    override fun showProgress() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun hideProgress() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun reloadList() {
        characterAdapter?.notifyDataSetChanged()
    }

    override fun showError(t: Throwable) {
        Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun init() {
        binding.swipeRefreshLayout.setOnRefreshListener(presenter::reload)
        characterAdapter =
            CharactersRVAdapter(presenter.charactersListPresenter, GlideImageLoader())
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = characterAdapter
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }
}
