package com.leaguechampions.robots;

import android.support.test.InstrumentationRegistry;

import com.leaguechampions.BuildConfig;
import com.leaguechampions.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class SettingsRobot {

    public SettingsRobot checkVersionIsDisplayed() {
        onView(withText(String.format(InstrumentationRegistry.getTargetContext().getString(R.string.version), BuildConfig.VERSION_NAME)))
                .check(matches(isDisplayed()));
        return this;
    }
}
