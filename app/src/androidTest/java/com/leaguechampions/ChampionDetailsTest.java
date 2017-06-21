package com.leaguechampions;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.leaguechampions.activity.ChampionsActivity;
import com.leaguechampions.core.Const;
import com.leaguechampions.utils.ElapsedTimeIdlingResource;
import com.leaguechampions.utils.PrefUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class ChampionDetailsTest {

    @Rule
    public ActivityTestRule<ChampionsActivity> activityTestRule = new ActivityTestRule<ChampionsActivity>(ChampionsActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            PrefUtils.setMockMode(InstrumentationRegistry.getTargetContext(), true);
            IdlingPolicies.setIdlingResourceTimeout(Const.BEHAVIOR_DELAY_MILLIS + 1000, TimeUnit.MILLISECONDS);
            super.beforeActivityLaunched();
        }
    };

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDisplayChampionDetails() throws Exception {
        showChampionDetails();
    }

    public void showChampionDetails() {
        Espresso.registerIdlingResources(new ElapsedTimeIdlingResource());

        // ChampionsActivity
        ViewInteraction rvChampions = onView(allOf(withId(R.id.activity_champions_rvChampions), isDisplayed()));
        rvChampions.perform(actionOnItemAtPosition(1, click()));

        Espresso.registerIdlingResources(new ElapsedTimeIdlingResource());

        // ChampionDetailsActivity
        onView(withId(R.id.activity_champion_details_tvName))
                .check(matches(isDisplayed()))
                .check(matches(withText("Riven")));
    }
}
