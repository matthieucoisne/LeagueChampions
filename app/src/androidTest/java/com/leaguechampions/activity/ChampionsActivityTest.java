package com.leaguechampions.activity;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.leaguechampions.robots.ChampionDetailsRobot;
import com.leaguechampions.robots.ChampionsRobot;
import com.leaguechampions.utils.PrefUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ChampionsActivityTest {

    @Rule
    public ActivityTestRule<ChampionsActivity> activityTestRule = new ActivityTestRule<ChampionsActivity>(ChampionsActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            PrefUtils.setMockMode(InstrumentationRegistry.getTargetContext(), true);
            super.beforeActivityLaunched();
        }
    };

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void displayChampionsList() {
        new ChampionsRobot()
                .verifyChampionsAreDisplayed();
    }

    @Test
    public void displayChampionsList_ClickChampion_DisplayChampionDetails() {
        new ChampionsRobot()
                .clickChampionAtPosition(1);

        new ChampionDetailsRobot()
                .verifyChampionIsDisplayed("Riven");
    }
}