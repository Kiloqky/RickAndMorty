package ru.kiloqky.gb.rickandmortymvp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.kiloqky.gb.rickandmortymvp.model.api.RickAndMortyApi
import ru.kiloqky.gb.rickandmortymvp.model.repository.RickAndMortyRepository
import ru.kiloqky.gb.rickandmortymvp.model.repository.RickAndMortyRepositoryImpl
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.RickAndMortyDataSource
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.cloud.CloudRickAndMortyDataSource
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    fun providesBaseUrl() = "https://rickandmortyapi.com/api/"

    @Provides
    fun providesGson(): Gson =
        GsonBuilder().create()

    @Provides
    @Singleton
    fun providesApi(baseUrl: String, gson: Gson): RickAndMortyApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RickAndMortyApi::class.java)

    @Provides
    fun provideRickAndMortyCloudDataSource(
        api: RickAndMortyApi
    ): RickAndMortyDataSource =
        CloudRickAndMortyDataSource(api)

    @Provides
    fun provideRickAndMortyRepository(
        cloudRickAndMortyDataSource: RickAndMortyDataSource
    ): RickAndMortyRepository = RickAndMortyRepositoryImpl(cloudRickAndMortyDataSource)

}