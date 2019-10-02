package com.leaguechampions.ui.settings

import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsActivityTest {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<com.leaguechampions.features.settings.SettingsActivity> = object : ActivityTestRule<com.leaguechampions.features.settings.SettingsActivity>(com.leaguechampions.features.settings.SettingsActivity::class.java) {
        override fun beforeActivityLaunched() {
            com.leaguechampions.core.utils.PrefUtils.setMockMode(InstrumentationRegistry.getTargetContext(), true)
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
