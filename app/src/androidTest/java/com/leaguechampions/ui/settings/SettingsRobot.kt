package com.leaguechampions.ui.settings

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.leaguechampions.BuildConfig
import com.leaguechampions.R

class SettingsRobot {

    fun checkVersionIsDisplayed(): SettingsRobot {
        onView(withText(String.format(InstrumentationRegistry.getTargetContext().getString(R.string.version), BuildConfig.VERSION_NAME)))
                .check(matches(isDisplayed()))
        return this
    }
}
