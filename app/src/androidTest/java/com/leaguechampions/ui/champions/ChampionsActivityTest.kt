package com.leaguechampions.ui.champions

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import com.leaguechampions.ui.championdetails.ChampionDetailsRobot
import com.leaguechampions.ui.settings.SettingsRobot
import com.leaguechampions.utils.PrefUtils

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChampionsActivityTest {

    @Rule
    var activityTestRule: ActivityTestRule<ChampionsActivity> = object : ActivityTestRule<ChampionsActivity>(ChampionsActivity::class.java) {
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