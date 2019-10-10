package com.leaguechampions.features.champions.presentation.championdetails

import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChampionDetailsActivityTest {

    companion object {
        private val championId = "Riven"
    }

    // TODO remove this test. Add one for the ChampionDetailsFragment

//    @Rule
//    @JvmField
//    var activityTestRule: ActivityTestRule<ChampionDetailsActivity> = object : ActivityTestRule<ChampionDetailsActivity>(ChampionDetailsActivity::class.java) {
//        override fun beforeActivityLaunched() {
//            PrefUtils.setMockMode(InstrumentationRegistry.getTargetContext(), true)
//            super.beforeActivityLaunched()
//        }
//
//        override fun getActivityIntent(): Intent {
//            return ChampionDetailsActivity.getIntent(InstrumentationRegistry.getTargetContext(), championId)
//        }
//    }

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
