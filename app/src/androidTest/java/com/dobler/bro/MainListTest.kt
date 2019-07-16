package com.dobler.bro

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import com.dobler.bro.ui.main.MainFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class MainListTest {

    @Rule
    @JvmField
    var wireMockRule = WireMockRule(8089)

    @Test
    fun onStartScreen_loadingMustBeDisplayed() {
        val mockNavController = Mockito.mock(NavController::class.java)
        val titleScenario = launchFragmentInContainer<MainFragment>()

        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.pbLoading)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onStartLoaded_ShowList() {
        val mockNavController = Mockito.mock(NavController::class.java)
        val titleScenario = launchFragmentInContainer<MainFragment>()

        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.pbLoading)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
