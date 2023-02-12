package com.github.vnord.derdiedas.di

import android.app.Application
import androidx.room.Room
import com.github.vnord.derdiedas.feature_nouns.data.data_source.NounDatabase
import com.github.vnord.derdiedas.feature_nouns.data.repository.NounRepositoryImpl
import com.github.vnord.derdiedas.feature_nouns.domain.repository.NounRepository
import com.github.vnord.derdiedas.feature_nouns.domain.use_case.AddNoun
import com.github.vnord.derdiedas.feature_nouns.domain.use_case.GetNouns
import com.github.vnord.derdiedas.feature_nouns.domain.use_case.NounUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideNounDatabase(app: Application): NounDatabase =
        Room.inMemoryDatabaseBuilder(app, NounDatabase::class.java).build()

    @Provides
    @Singleton
    fun provideNounRepository(db: NounDatabase): NounRepository = NounRepositoryImpl(db.nounDao)

    @Provides
    @Singleton
    fun provideNounUseCases(repository: NounRepository): NounUseCases =
        NounUseCases(getNouns = GetNouns(repository), addNoun = AddNoun(repository))
}
