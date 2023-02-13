package com.github.vnord.derdiedas.di

import android.app.Application
import androidx.room.Room
import com.github.vnord.derdiedas.feature.nouns.data.source.NounDatabase
import com.github.vnord.derdiedas.feature.nouns.data.repository.NounRepositoryImpl
import com.github.vnord.derdiedas.feature.nouns.domain.repository.NounRepository
import com.github.vnord.derdiedas.feature.nouns.domain.usecase.AddNoun
import com.github.vnord.derdiedas.feature.nouns.domain.usecase.GetNouns
import com.github.vnord.derdiedas.feature.nouns.domain.usecase.NounUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNounDatabase(app: Application): NounDatabase =
        Room.databaseBuilder(app, NounDatabase::class.java, NounDatabase.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideNounRepository(db: NounDatabase): NounRepository = NounRepositoryImpl(db.nounDao)

    @Provides
    @Singleton
    fun provideNounUseCases(repository: NounRepository): NounUseCases = NounUseCases(
        addNoun = AddNoun(repository),
        getNouns = GetNouns(repository)
    )
}
