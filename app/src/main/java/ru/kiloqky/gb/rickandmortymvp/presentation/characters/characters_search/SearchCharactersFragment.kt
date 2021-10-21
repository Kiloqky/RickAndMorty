package ru.kiloqky.gb.rickandmortymvp.presentation.characters.characters_search

import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import moxy.ktx.moxyPresenter
import ru.kiloqky.gb.rickandmortymvp.IScreens
import ru.kiloqky.gb.rickandmortymvp.R
import ru.kiloqky.gb.rickandmortymvp.databinding.FragmentCharactersBinding
import ru.kiloqky.gb.rickandmortymvp.databinding.FragmentCharactersSearchBinding
import ru.kiloqky.gb.rickandmortymvp.helpers.toast
import ru.kiloqky.gb.rickandmortymvp.model.repository.RickAndMortyRepository
import ru.kiloqky.gb.rickandmortymvp.model.imageloader.GlideImageLoader
import ru.kiloqky.gb.rickandmortymvp.presentation.abs.AbsFragment
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.adapter.CharactersRVAdapter
import ru.kiloqky.gb.rickandmortymvp.scheduler.Schedulers
import javax.inject.Inject

class SearchCharactersFragment : AbsFragment(R.layout.fragment_characters_search),
    SearchCharactersView {

    private val binding: FragmentCharactersSearchBinding by viewBinding()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var repository: RickAndMortyRepository

    var name: String = ""
    private val presenter: SearchCharactersPresenter by moxyPresenter {
        SearchCharactersPresenter(router, screens, schedulers, repository)
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

    override fun showToast(msg: String) {
        requireContext().toast(msg)
    }

    override fun init() {
        binding.searchView.setOnQueryTextListener(listener)
        binding.swipeRefreshLayout.setOnRefreshListener { presenter.reload(name) }
        characterAdapter =
            CharactersRVAdapter(presenter.charactersListPresenter, GlideImageLoader())
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = characterAdapter
    }

    private val listener = object : OnQueryTextListener() {
        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let {
                presenter.reload(it)
                return true
            }
            return false
        }
    }

    companion object {
        fun newInstance() = SearchCharactersFragment()
    }
}
