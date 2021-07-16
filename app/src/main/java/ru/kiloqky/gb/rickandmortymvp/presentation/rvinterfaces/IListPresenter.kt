package ru.kiloqky.gb.rickandmortymvp.presentation.rvinterfaces

interface IListPresenter<V : ItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}