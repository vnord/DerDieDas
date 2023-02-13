package com.github.vnord.derdiedas.featurenouns.domain.usecase

import com.github.vnord.derdiedas.featurenouns.data.repository.FakeNounRepository
import com.github.vnord.derdiedas.feature.nouns.domain.model.Gender
import com.github.vnord.derdiedas.feature.nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature.nouns.domain.usecase.GetNouns
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNounsTest {
    private lateinit var getNouns: GetNouns
    private lateinit var fakeRepository: FakeNounRepository

    private val testNouns = ('A'..'Z').map { Noun(it.toString(), Gender.values().random()) }

    @Before
    fun setUp() {
        fakeRepository = FakeNounRepository()
        getNouns = GetNouns(fakeRepository)

        runBlocking { testNouns.shuffled().forEach { fakeRepository.insertNoun(it) } }
    }

    @Test
    fun `get nouns`(): Unit = runBlocking {
        // This is kinda dumb right now, but will probably be more relevant once we start
        // adding more business logic to `getNouns`
        assertThat(getNouns().first()).containsExactlyElementsIn(testNouns)
    }
}
