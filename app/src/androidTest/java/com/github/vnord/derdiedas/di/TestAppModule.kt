package com.github.vnord.derdiedas.di

import android.app.Application
import androidx.room.Room
import com.github.vnord.derdiedas.data.repository.NounRepositoryImpl
import com.github.vnord.derdiedas.data.source.NounDatabase
import com.github.vnord.derdiedas.domain.model.Categories
import com.github.vnord.derdiedas.domain.repository.NounRepository
import com.github.vnord.derdiedas.domain.usecase.AddNoun
import com.github.vnord.derdiedas.domain.usecase.GetCategories
import com.github.vnord.derdiedas.domain.usecase.GetNextNoun
import com.github.vnord.derdiedas.domain.usecase.GetNouns
import com.github.vnord.derdiedas.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideNounDatabase(app: Application): NounDatabase =
        runBlocking { NounDatabase.create(app.applicationContext) }

    @Provides
    @Singleton
    fun provideNounRepository(db: NounDatabase): NounRepository = NounRepositoryImpl(db.nounDao)

    @Provides
    @Singleton
    fun provideNounUseCases(repository: NounRepository): UseCases =
        UseCases(
            getNouns = GetNouns(repository),
            addNoun = AddNoun(repository),
            getNextNoun = GetNextNoun(repository),
            getCategories = GetCategories(repository),
        )
}
