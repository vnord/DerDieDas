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
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class NounDaoTests {
    private val db = NounRoomDatabase.getDatabase(ApplicationProvider.getApplicationContext())
    private val nounDao = db.nounDao()

    private val nouns = listOf(Noun("Mann", Gender.DER), Noun("Frau", Gender.DIE))

    @Before
    fun clearDb() = db.clearAllTables()

    @Test
    fun insertAndGet() = runTest {
        nouns.forEach { nounDao.insert(it) }
        // TODO: use assertThat(nounDao.getNouns().first()).contains?? pattern instead
        assertEquals(nouns.sortedBy { it.noun }, nounDao.getNouns().first().sortedBy { it.noun })
        assertEquals(2, nounDao.getNumberOfNouns())
        assertEquals(nouns.sortedBy { it.noun }, nounDao.getEligibleNouns().sortedBy { it.noun })
        assertEquals(2, nounDao.getNumberOfEligibleNouns().first())
        assertEquals(nouns.first(), nounDao.getNthNoun(1))
        assertEquals(nouns.last(), nounDao.getNthNoun(0))
    }

    @Test
    fun delete() = runTest {
        nouns.forEach { nounDao.insert(it) }
        nounDao.delete(nouns[0])
        assertEquals(nouns[1], nounDao.getNouns().first().single())
    }

    @Test
    fun update() = runTest {
        nouns.forEach { nounDao.insert(it) }
        assertTrue(nouns.none { it.gender == Gender.DAS })
        val updates = nouns.map { it.copy(gender = Gender.DAS) }
        assertTrue(updates.all { it.gender == Gender.DAS })
        updates.forEach { nounDao.update(it) }
        assertEquals(updates.sortedBy { it.noun }, nounDao.getNouns().first().sortedBy { it.noun })
    }
}
