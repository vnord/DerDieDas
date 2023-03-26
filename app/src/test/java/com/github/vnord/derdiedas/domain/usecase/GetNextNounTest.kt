package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.data.entity.Category
import com.github.vnord.derdiedas.data.entity.Noun
import com.github.vnord.derdiedas.data.entity.Noun.Gender.MASCULINE
import com.github.vnord.derdiedas.data.entity.Noun.Gender.NEUTER
import com.github.vnord.derdiedas.data.repository.FakeNounRepository
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

    private val nouns = listOf(Noun("Haus", NEUTER), Noun("Mann", MASCULINE))
    private val category = Category("My Nouns", 0)

    @Test
    fun `when there are no nouns in MyNouns, first noun and next noun should be null`() =
        runBlocking {
            assertThat(getNextNoun.getFirstNoun(category = category)).isNull()
            assertThat(getNextNoun()).isNull()
        }

    @Test
    fun `when there are nouns in repository, should return a noun`() = runBlocking {
        nouns.forEach { fakeRepository.insertNoun(it, category) }
        assertThat(getNextNoun.getFirstNoun(category)).isEqualTo(nouns[0])
        assertThat(getNextNoun()).isEqualTo(nouns[1])
    }

    @Test
    fun `when there is a single noun in the repository, it should be removed`() {
        runBlocking {
            fakeRepository.insertNoun(nouns.first(), category)
            getNextNoun.getFirstNoun(category)
            assertThat(getNextNoun()).isNull()
        }
    }
}
