package com.leaguechampions.ui.champions

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import com.leaguechampions.R

class ChampionsRobot {

    fun verifyChampionsAreDisplayed(): ChampionsRobot {
        onView(withId(R.id.activity_champions_rvChampions))
                .check(matches(isDisplayed()))
        return this
    }

    fun clickChampionAtPosition(position: Int): ChampionsRobot {
        onView(withId(R.id.activity_champions_rvChampions))
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
