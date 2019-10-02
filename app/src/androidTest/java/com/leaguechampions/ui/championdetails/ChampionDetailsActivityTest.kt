package com.leaguechampions.ui.championdetails

import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4

import com.leaguechampions.core.utils.PrefUtils

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChampionDetailsActivityTest {

    companion object {
        private val championId = "Riven"
    }

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<ChampionDetailsActivity> = object : ActivityTestRule<ChampionDetailsActivity>(ChampionDetailsActivity::class.java) {
        override fun beforeActivityLaunched() {
            com.leaguechampions.core.utils.PrefUtils.setMockMode(InstrumentationRegistry.getTargetContext(), true)
            super.beforeActivityLaunched()
        }

        override fun getActivityIntent(): Intent {
            return ChampionDetailsActivity.getIntent(InstrumentationRegistry.getTargetContext(), championId)
        }
    }

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun displayChampionDetails() {
        ChampionDetailsRobot()
                .verifyChampionIsDisplayed(championId)
    }
}
