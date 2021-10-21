package ru.kiloqky.gb.rickandmortymvp.di

import android.content.Context
import androidx.room.Room
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
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.cache.CacheRickAndMortyDataSource
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.cache.CacheRickAndMortyDataSourceImpl
import ru.kiloqky.gb.rickandmortymvp.model.repository.datasource.cloud.CloudRickAndMortyDataSource
import ru.kiloqky.gb.rickandmortymvp.model.storage.CharacterStorage
import ru.kiloqky.gb.rickandmortymvp.model.storage.EpisodeStorage
import ru.kiloqky.gb.rickandmortymvp.model.storage.LocationStorage
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

    @Singleton
    @Provides
    fun provideCharacterStorage(context: Context) =
        Room.databaseBuilder(
            context,
            CharacterStorage::class.java,
            "character_table"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideLocationStorage(context: Context) =
        Room.databaseBuilder(
            context,
            LocationStorage::class.java,
            "location_table"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideEpisodesStorage(context: Context) =
        Room.databaseBuilder(
            context,
            EpisodeStorage::class.java,
            "episodes_table"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideRickAndMortyCloudDataSource(
        api: RickAndMortyApi
    ): RickAndMortyDataSource =
        CloudRickAndMortyDataSource(api)

    @Provides
    fun provideRickAndMortyCacheDataSource(
        characterStorage: CharacterStorage,
        locationStorage: LocationStorage,
        episodeStorage: EpisodeStorage
    ): CacheRickAndMortyDataSource =
        CacheRickAndMortyDataSourceImpl(
            characterStorage,
            locationStorage,
            episodeStorage
        )

    @Provides
    fun provideRickAndMortyRepository(
        cloudRickAndMortyDataSource: RickAndMortyDataSource,
        cacheRickAndMortyDataSource: CacheRickAndMortyDataSource
    ): RickAndMortyRepository = RickAndMortyRepositoryImpl(cloudRickAndMortyDataSource, cacheRickAndMortyDataSource)


}