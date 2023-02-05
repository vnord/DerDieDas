package com.github.vnord.derdiedas

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.data.Noun
import com.github.vnord.derdiedas.data.NounRoomDatabase
import com.github.vnord.derdiedas.view.StartFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class NavigationTests {
    private val db = NounRoomDatabase.getDatabase(ApplicationProvider.getApplicationContext())
    private val nounDao = db.nounDao()
    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun clearDb() = db.clearAllTables()

    @Test
    fun startFragmentToNounListFragment() {
        launchFragmentInContainer<StartFragment>().onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.list_button)).perform(click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.NounListFragment)
    }

    @Test
    fun startFragmentToDoneFragment() = runTest {
        launchFragmentInContainer<StartFragment>().onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.start_button)).perform(click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.DoneFragment)
    }

    @Test
    fun startFragmentToQuizFragment() = runTest {
        nounDao.insert(Noun("Mann", Gender.DER))

        launchFragmentInContainer<StartFragment>().onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.start_button)).perform(click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.QuizFragment)
    }
}
