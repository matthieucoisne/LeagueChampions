package com.leaguechampions.ui.championdetails

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.leaguechampions.R

class ChampionDetailsRobot {

    fun verifyChampionIsDisplayed(championId: String): ChampionDetailsRobot {
        onView(withId(R.id.activity_champion_details_tvName))
                .check(matches(withText(championId)))
        return this
    }
}
