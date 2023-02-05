package com.github.vnord.derdiedas

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.vnord.derdiedas.view.StartFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTests {
    @Test
    fun testNavigation() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInContainer<StartFragment>().onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)

            setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.list_button)).perform(click())
        assertEquals(R.id.nounListFragment, navController.currentDestination?.id)
    }
}
