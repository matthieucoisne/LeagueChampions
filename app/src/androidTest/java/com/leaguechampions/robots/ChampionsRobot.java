package com.leaguechampions.robots;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;

import com.leaguechampions.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ChampionsRobot {

    public ChampionsRobot verifyChampionsAreDisplayed() {
        onView(withId(R.id.activity_champions_rvChampions))
                .check(matches(isDisplayed()));
        return this;
    }

    public ChampionsRobot clickChampionAtPosition(int position) {
        onView(withId(R.id.activity_champions_rvChampions))
                .perform(actionOnItemAtPosition(position, click()));
        return this;
    }

    public ChampionsRobot openMenu() {
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        return this;
    }

    public ChampionsRobot clickMenuSettings() {
        onView(withText(R.string.settings))
                .perform(click());
        return this;
    }
}
