package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.data.repository.FakeNounRepository
import com.github.vnord.derdiedas.domain.model.Gender
import com.github.vnord.derdiedas.domain.model.Noun
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNextNounTest {
    private lateinit var getNextNoun: GetNextNoun
    private lateinit var fakeRepository: FakeNounRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNounRepository()
        getNextNoun = GetNextNoun(fakeRepository)
    }

    private val noun = Noun("Haus", Gender.NEUTER)

    @Test
    fun `when there are no nouns in repository, should return null`() = runBlocking {
        assertThat(getNextNoun()).isNull()
    }

    @Test
    fun `when there are nouns in repository, should return a noun`() = runBlocking {
        fakeRepository.insertNoun(noun)
        assertThat(getNextNoun()).isEqualTo(noun)
    }

    @Test
    fun `when there is a single noun in the repository, it should be removed`() {
        runBlocking {
            fakeRepository.insertNoun(noun)
            getNextNoun()
            assertThat(getNextNoun()).isNull()
        }
    }
}
