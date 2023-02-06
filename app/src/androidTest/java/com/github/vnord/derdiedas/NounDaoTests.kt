package com.github.vnord.derdiedas

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.data.Noun
import com.github.vnord.derdiedas.data.NounRoomDatabase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class NounDaoTests {
    private val db = NounRoomDatabase.getDatabase(ApplicationProvider.getApplicationContext())
    private val nounDao = db.nounDao()

    private val nouns = arrayOf(Noun("Mann", Gender.DER), Noun("Frau", Gender.DIE))

    @Before
    fun clearDb() = db.clearAllTables()

    @Test
    fun insertAndGet() = runTest {
        nouns.forEach { nounDao.insert(it) }
        assertThat(nounDao.getNouns().first()).containsExactlyInAnyOrder(*nouns)
        assertThat(nounDao.getNumberOfNouns()).isEqualTo(2)
        assertThat(nounDao.getEligibleNouns()).containsExactly(*nouns)
        assertThat(nounDao.getNumberOfEligibleNouns().first()).isEqualTo(2)
        assertThat(nounDao.getNthNoun(1)).isEqualTo(nouns[0])
        assertThat(nounDao.getNthNoun(0)).isEqualTo(nouns[1])
    }

    @Test
    fun delete() = runTest {
        nouns.forEach { nounDao.insert(it) }
        nounDao.delete(nouns[0])
        assertThat(nounDao.getNouns().first()).doesNotContain(nouns[0])
        assertThat(nounDao.getNumberOfNouns()).isEqualTo(1)
    }

    @Test
    fun update() = runTest {
        nouns.forEach { nounDao.insert(it) }
        assertTrue(nouns.none { it.gender == Gender.DAS })
        val updates = nouns.map { it.copy(gender = Gender.DAS) }.toTypedArray()
        assertTrue(updates.all { it.gender == Gender.DAS })
        updates.forEach { nounDao.update(it) }
        assertEquals(updates.sortedBy { it.noun }, nounDao.getNouns().first().sortedBy { it.noun })
        assertThat(nounDao.getNouns().first()).containsExactlyInAnyOrder(*updates)
    }
}
