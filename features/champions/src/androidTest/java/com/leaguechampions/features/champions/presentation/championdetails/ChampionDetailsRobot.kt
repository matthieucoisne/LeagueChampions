package com.leaguechampions.features.champions.presentation.championdetails

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.leaguechampions.features.champions.R

class ChampionDetailsRobot {

    fun verifyChampionIsDisplayed(championId: String): ChampionDetailsRobot {
        onView(withId(R.id.tvName))
                .check(matches(withText(championId)))
        return this
    }
}
