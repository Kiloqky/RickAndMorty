package ru.kiloqky.gb.rickandmortymvp.presentation.characters

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.kiloqky.gb.rickandmortymvp.IScreens
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.entities.CharacterResult
import ru.kiloqky.gb.rickandmortymvp.model.repository.RickAndMortyRepository
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.adapter.CharacterItemView
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.adapter.ICharactersListPresenter
import ru.kiloqky.gb.rickandmortymvp.scheduler.Schedulers

class CharactersPresenter(
    private val router: Router,
    private val screens: IScreens,
    private val schedulers: Schedulers,
    private val repository: RickAndMortyRepository
) : MvpPresenter<CharactersView>() {

    private val disposables = CompositeDisposable()

    inner class CharactersListPresenter : ICharactersListPresenter {

        val characterList: MutableList<Character> = mutableListOf()
        override var itemClickListener: ((CharacterItemView) -> Unit)? = null
        override fun bindView(view: CharacterItemView) {
            view.setInfo(characterList[view.pos])
            /*
            * Это типа пагинация, работает на оптимизации списка recycler view,
            * как только на экране показывается последний элемент списка, мы прогружаем следующие данные.
            * Я понял, что для этого не обязательно нужен onScrollListener,
            * конечно в будущем я с ним сделаю, но пока что пусть будет вот так, тем более что это хорошо работает.
            * */
            if (characterList.size - 1 == view.pos) {
                loadData()
            }
        }

        override fun getCount(): Int = characterList.size
    }

    val charactersListPresenter = CharactersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        charactersListPresenter.itemClickListener = { characterItemView ->
            navigateToCharacterDetails(charactersListPresenter.characterList[characterItemView.pos].id)
        }
        loadData()
    }

    private fun navigateToCharacterDetails(id: Int) {
        router.navigateTo(screens.CharacterScreen(id))
    }

    private fun loadData() {
        viewState.showProgress()
        disposables += if (charactersListPresenter.characterList.size == 0)
            loadCharacter()
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(::onGetCharacterSuccess, ::onError)
        else
            loadNextPage()
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(::onGetCharacterSuccess, ::onError)
    }

    private var nextPageUrl: String? = null

    private fun loadCharacter(): Single<List<Character>> =
        repository
            .loadCharacters()
            .flatMap(::initNextPage)

    private fun initNextPage(result: CharacterResult): Single<List<Character>> {
        nextPageUrl = result.info.next
        return Single.just(result.characters)
    }

    private fun loadNextPage(): Single<List<Character>> {
        val nextPageUrl = nextPageUrl
        this.nextPageUrl = null
        return if (nextPageUrl == null) {
            Single.error(IllegalArgumentException("that's all"))
        } else {
            repository
                .loadNextPageCharacter(nextPageUrl)
                .flatMap(::initNextPage)
        }
    }

    private fun onGetCharacterSuccess(result: List<Character>) {
        viewState.hideProgress()
        val startSize = charactersListPresenter.characterList.size
        charactersListPresenter.characterList.addAll(result)
        val endSize = charactersListPresenter.characterList.size
        Log.d("CharactersPresenter", "$startSize - $endSize")
        viewState.updateList(startSize, endSize)
    }

    fun reload() {
        nextPageUrl = null
        loadCharacter()
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe(::onReloadSuccess, ::onError)
    }

    private fun onReloadSuccess(list: List<Character>) {
        charactersListPresenter.characterList.clear()
        charactersListPresenter.characterList.addAll(list)
        viewState.reloadList()
        viewState.hideProgress()
    }

    private fun onError(t: Throwable) {
        viewState.showError(t)
        viewState.hideProgress()
    }

}