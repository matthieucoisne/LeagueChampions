package com.leaguechampions.robots;

import com.leaguechampions.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

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
}
