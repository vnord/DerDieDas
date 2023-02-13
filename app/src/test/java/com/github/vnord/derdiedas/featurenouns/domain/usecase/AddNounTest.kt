package com.github.vnord.derdiedas.featurenouns.domain.usecase

import com.github.vnord.derdiedas.feature.nouns.domain.model.Gender
import com.github.vnord.derdiedas.feature.nouns.domain.model.Noun
import com.github.vnord.derdiedas.feature.nouns.domain.usecase.AddNoun
import com.github.vnord.derdiedas.featurenouns.data.repository.FakeNounRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNounTest {
    private lateinit var addNoun: AddNoun
    private lateinit var fakeRepository: FakeNounRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNounRepository()
        addNoun = AddNoun(fakeRepository)
    }

    @Test
    fun `add noun`(): Unit = runBlocking {
        // Again, these tests will be more relevant once we have more business logic
        val noun = Noun("Mann", Gender.DER)
        addNoun(noun)
        assertThat(fakeRepository.getNouns().first().single()).isEqualTo(noun)
    }
}
