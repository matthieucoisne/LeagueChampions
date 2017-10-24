package com.leaguechampions.ui.championdetails;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.leaguechampions.utils.PrefUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ChampionDetailsTest {

    private static final String championId = "Riven";

    @Rule
    public ActivityTestRule<ChampionDetailsActivity> activityTestRule = new ActivityTestRule<ChampionDetailsActivity>(ChampionDetailsActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            PrefUtils.setMockMode(InstrumentationRegistry.getTargetContext(), true);
            super.beforeActivityLaunched();
        }

        @Override
        protected Intent getActivityIntent() {
            return ChampionDetailsActivity.getIntent(InstrumentationRegistry.getTargetContext(), "", championId);
        }
    };

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void displayChampionDetails() {
        new ChampionDetailsRobot()
                .verifyChampionIsDisplayed(championId);
    }
}
