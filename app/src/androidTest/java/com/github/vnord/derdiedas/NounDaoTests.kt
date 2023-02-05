package com.github.vnord.derdiedas

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.data.Noun
import com.github.vnord.derdiedas.data.NounRoomDatabase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NounDaoTests {
    private val db by lazy {
        val context = ApplicationProvider.getApplicationContext<Context>()
        Room.inMemoryDatabaseBuilder(context, NounRoomDatabase::class.java).build()
    }
    private val nounDao by lazy { db.nounDao() }


    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() = Dispatchers.setMain(mainThreadSurrogate)

    private val nouns = listOf(Noun("Mann", Gender.DER), Noun("Frau", Gender.DIE))

    @After
    fun closeDb() {
        db.close()
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun insertAndGet() = runTest {
        nouns.forEach { nounDao.insert(it) }
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
