package com.leaguechampions.ui.settings

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.leaguechampions.utils.PrefUtils
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsActivityTest {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<SettingsActivity> = object : ActivityTestRule<SettingsActivity>(SettingsActivity::class.java) {
        override fun beforeActivityLaunched() {
            PrefUtils.setMockMode(InstrumentationRegistry.getTargetContext(), true)
            super.beforeActivityLaunched()
        }
    }

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun checkVersionIsDisplayed() {
        SettingsRobot()
                .checkVersionIsDisplayed()
    }
}