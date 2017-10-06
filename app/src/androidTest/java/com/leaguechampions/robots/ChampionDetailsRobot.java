package com.leaguechampions.robots;

import com.leaguechampions.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ChampionDetailsRobot {

    public ChampionDetailsRobot verifyChampionIsDisplayed(String championId) {
        onView(withId(R.id.activity_champion_details_tvName))
                .check(matches(withText(championId)));
        return this;
    }
}
