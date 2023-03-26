package com.github.vnord.derdiedas.domain.usecase

import com.github.vnord.derdiedas.data.repository.FakeNounRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCategoriesTest {
    private lateinit var getCategories: GetCategories
    private lateinit var fakeRepository: FakeNounRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNounRepository()
        getCategories = GetCategories(fakeRepository)
    }


    @Test
    fun testInvoke(): Unit =
        runBlocking {
            assertThat(getCategories().first()).containsExactly(fakeRepository.category)
        }

}
