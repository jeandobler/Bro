package com.dobler.bro

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.rule.ActivityTestRule
import com.dobler.bro.ui.main.MainFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class MainListTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testNavigationToInGameScreen() {

        val mockNavController = mock(NavController::class.java)

        val titleScenario = launchFragmentInContainer<MainFragment>()

        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

//        Verify that performing a click prompts the correct Navigation action
//        onView(ViewMatchers.withId(R.id.play_btn)).perform(ViewActions.click())
//        verify(mockNavController).navigate(R.id.action_title_screen_to_in_game)
    }

}
