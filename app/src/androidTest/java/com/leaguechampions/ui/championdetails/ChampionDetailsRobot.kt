package com.leaguechampions.ui.championdetails

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.leaguechampions.R

class ChampionDetailsRobot {

    fun verifyChampionIsDisplayed(championId: String): ChampionDetailsRobot {
        onView(withId(R.id.activity_champion_details_tvName))
                .check(matches(withText(championId)))
        return this
    }
}
