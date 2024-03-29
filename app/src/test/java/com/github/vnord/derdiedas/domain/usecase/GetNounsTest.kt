package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.data.repository.FakeNounRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNounsTest {
    private lateinit var getNouns: GetNouns
    private lateinit var fakeRepository: FakeNounRepository

    private val testNouns = ('A'..'Z').map { Noun(it.toString(), Noun.Gender.values().random()) }

    @Before
    fun setUp() {
        fakeRepository = FakeNounRepository()
        getNouns = GetNouns(fakeRepository)

        runBlocking { testNouns.shuffled().forEach { fakeRepository.insertNoun(it, fakeRepository.category) } }
    }

    @Test
    fun `get nouns`(): Unit = runBlocking {
        // This is kinda dumb right now, but will probably be more relevant once we start
        // adding more business logic to `getNouns`
        assertThat(getNouns(limit = 100).first()).containsExactlyElementsIn(testNouns)
    }
}
