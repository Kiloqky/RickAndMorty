package ru.kiloqky.gb.rickandmortymvp.presentation.characters.character

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.kiloqky.gb.rickandmortymvp.IScreens
import ru.kiloqky.gb.rickandmortymvp.model.repository.RickAndMortyRepository
import ru.kiloqky.gb.rickandmortymvp.scheduler.Schedulers

class CharacterPresenter(
    private val characterId: Int,
    private val router: Router,
    private val screens: IScreens,
    private val schedulers: Schedulers,
    private val repository: RickAndMortyRepository
) : MvpPresenter<CharacterView>() {

    private val disposables = CompositeDisposable()

    fun unknownId(): Int {
        router.exit()
        return 0
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposables +=
            repository.loadCharacterById(characterId)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(viewState::init, viewState::onError)
    }
}