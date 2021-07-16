package ru.kiloqky.gb.rickandmortymvp.scheduler

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import ru.kiloqky.gb.rickandmortymvp.scheduler.Schedulers

class DefaultSchedulers : Schedulers {

    override fun background(): Scheduler = io.reactivex.rxjava3.schedulers.Schedulers.io()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}