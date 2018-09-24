package com.leaguechampions.ui.champions

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.recyclerview.widget.RecyclerView
import com.leaguechampions.R

class ChampionsRobot {

    fun verifyChampionsAreDisplayed(): ChampionsRobot {
        onView(withId(R.id.activity_champions_rvChampions))
                .check(matches(isDisplayed()))
        return this
    }

    fun clickChampionAtPosition(position: Int): ChampionsRobot {
        onView(withId(R.id.activity_champions_rvChampions))
                .perform(actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(position, click()))
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
