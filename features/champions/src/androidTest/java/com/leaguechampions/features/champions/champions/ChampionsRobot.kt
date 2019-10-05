package com.leaguechampions.features.champions.champions

import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.leaguechampions.features.champions.R

class ChampionsRobot {

    fun verifyChampionsAreDisplayed(): ChampionsRobot {
        onView(withId(R.id.rvChampions))
                .check(matches(isDisplayed()))
        return this
    }

    fun clickChampionAtPosition(position: Int): ChampionsRobot {
        onView(withId(R.id.rvChampions))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
        return this
    }

    fun openMenu(): ChampionsRobot {
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext())
        return this
    }

    fun clickMenuSettings(): ChampionsRobot {
        onView(withText(R.string.settings))
                .perform(click())
        return this
    }
}
