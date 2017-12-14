package com.leaguechampions.ui.championdetails

import android.content.Intent
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
class ChampionDetailsActivityTest {

    companion object {
        private val championId = "Riven"
    }

    @Rule
    var activityTestRule: ActivityTestRule<ChampionDetailsActivity> = object : ActivityTestRule<ChampionDetailsActivity>(ChampionDetailsActivity::class.java) {
        override fun beforeActivityLaunched() {
            PrefUtils.setMockMode(InstrumentationRegistry.getTargetContext(), true)
            super.beforeActivityLaunched()
        }

        override fun getActivityIntent(): Intent {
            return ChampionDetailsActivity.getIntent(InstrumentationRegistry.getTargetContext(), "", championId)
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
