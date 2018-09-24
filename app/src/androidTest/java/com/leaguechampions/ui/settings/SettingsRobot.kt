package com.leaguechampions.ui.settings

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.leaguechampions.BuildConfig
import com.leaguechampions.R

class SettingsRobot {

    fun checkVersionIsDisplayed(): SettingsRobot {
        onView(withText(String.format(InstrumentationRegistry.getTargetContext().getString(R.string.version), BuildConfig.VERSION_NAME)))
                .check(matches(isDisplayed()))
        return this
    }
}
