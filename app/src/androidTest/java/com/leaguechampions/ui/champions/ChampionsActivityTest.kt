package com.leaguechampions.ui.champions

import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.leaguechampions.features.champions.champions.champions.ChampionsActivity

import com.leaguechampions.ui.championdetails.ChampionDetailsRobot
import com.leaguechampions.ui.settings.SettingsRobot
import com.leaguechampions.core.utils.PrefUtils

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChampionsActivityTest {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<ChampionsActivity> = object : ActivityTestRule<ChampionsActivity>(ChampionsActivity::class.java) {
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
    fun displayChampionsList() {
        ChampionsRobot()
                .verifyChampionsAreDisplayed()
    }

    @Test
    fun displayChampionsList_ClickChampion_DisplayChampionDetails() {
        ChampionsRobot()
                .clickChampionAtPosition(1)

        ChampionDetailsRobot()
                .verifyChampionIsDisplayed("Riven")
    }

    @Test
    fun showSettings() {
        ChampionsRobot()
                .openMenu()
                .clickMenuSettings()

        SettingsRobot()
                .checkVersionIsDisplayed()
    }
}
