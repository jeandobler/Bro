package com.dobler.bro

import androidx.navigation.NavController
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainListTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun whenNameFieldIsEmpty_pressConvertButton_displayWarningMessage() {

        // Create a mock NavController
//        val mockNavController = mock(NavController::class.java)
//
//        // Create a graphical FragmentScenario for the TitleScreen
//        val titleScenario = launchFragmentInContainer<TitleScreen>()
//
//
//        arrangeRobot {
//            mockHomeIntent()
//        }
//        actRobot {
//            setName("")
//            clickOnConvertButton()
//        }
//        assertRobot {
//            matchTextOnCard("WRITE YOUR NAME FIRST")
//        }
    }



}
