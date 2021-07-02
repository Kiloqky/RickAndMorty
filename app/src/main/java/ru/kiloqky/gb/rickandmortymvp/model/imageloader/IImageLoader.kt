package ru.kiloqky.gb.rickandmortymvp.model.imageloader

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}