package ru.kiloqky.gb.rickandmortymvp.presentation.characters.characters_search

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import moxy.MvpPresenter
import ru.kiloqky.gb.rickandmortymvp.IScreens
import ru.kiloqky.gb.rickandmortymvp.helpers.toast
import ru.kiloqky.gb.rickandmortymvp.model.entities.Character
import ru.kiloqky.gb.rickandmortymvp.model.entities.CharacterResult
import ru.kiloqky.gb.rickandmortymvp.model.repository.RickAndMortyRepository
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.adapter.CharacterItemView
import ru.kiloqky.gb.rickandmortymvp.presentation.characters.adapter.ICharactersListPresenter
import ru.kiloqky.gb.rickandmortymvp.scheduler.Schedulers

class SearchCharactersPresenter(
    private val router: Router,
    private val screens: IScreens,
    private val schedulers: Schedulers,
    private val repository: RickAndMortyRepository
) : MvpPresenter<SearchCharactersView>() {

    private val disposables = CompositeDisposable()

    private val behaviorSubject: BehaviorSubject<String> = BehaviorSubject.create()

    inner class CharactersListPresenter : ICharactersListPresenter {

        val characterList: MutableList<Character> = mutableListOf()
        override var itemClickListener: ((CharacterItemView) -> Unit)? = null
        override fun bindView(view: CharacterItemView) {
            view.setInfo(characterList[view.pos])
            /**
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
        behaviorSubject.observeOn(schedulers.main()).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                d?.let { disposables += it }
            }

            override fun onNext(t: String?) {
                t?.let(viewState::showToast)
            }

            override fun onError(e: Throwable?) { viewState::showError }

            override fun onComplete() {}

        })
    }

    private fun navigateToCharacterDetails(id: Int) {
        router.navigateTo(screens.CharacterScreen(id))
    }

    private fun loadData(name: String = "") {
        viewState.showProgress()
        disposables += if (name != "")
            loadCharacter(name)
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

    private fun loadCharacter(name: String): Single<List<Character>> =
        repository.searchCharactersByName(name)
            .flatMap(::initNextPage)

    private fun initNextPage(result: CharacterResult): Single<List<Character>> {
        behaviorSubject.onNext(result.info.next)
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
                .loadCharactersByUrl(nextPageUrl)
                .flatMap(::initNextPage)
        }
    }

    private fun onGetCharacterSuccess(result: List<Character>) {
        viewState.hideProgress()
        val startSize = charactersListPresenter.characterList.size
        charactersListPresenter.characterList.addAll(result)
        val endSize = charactersListPresenter.characterList.size
        viewState.updateList(startSize, endSize)
    }

    fun reload(name: String) {
        nextPageUrl = null
        loadCharacter(name)
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