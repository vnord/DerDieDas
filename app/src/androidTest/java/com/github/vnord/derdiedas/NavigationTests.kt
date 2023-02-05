package com.github.vnord.derdiedas

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.vnord.derdiedas.view.StartFragment
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTests {
    @Test
    fun testNavigation() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInContainer<StartFragment>().onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.action_StartFragment_to_nounListFragment)).perform(click())
        assertEquals(R.id.nounListFragment, navController.currentDestination?.id)
    }
}
