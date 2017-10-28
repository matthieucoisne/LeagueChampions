package com.leaguechampions.ui.settings;

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
public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> activityTestRule = new ActivityTestRule<SettingsActivity>(SettingsActivity.class) {
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
    public void checkVersionIsDisplayed() {
        new SettingsRobot()
                .checkVersionIsDisplayed();
    }
}