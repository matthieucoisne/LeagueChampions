package com.leaguechampions.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem

import com.leaguechampions.BuildConfig
import com.leaguechampions.utils.PrefUtils

import javax.inject.Inject

class SettingsPresenter @Inject constructor(private val view: SettingsView,
                                            private val preferences: SharedPreferences) {

    // Workaround for BuildConfig.BUILD_TYPE in methods for Unit Tests
    private val buildType = BuildConfig.BUILD_TYPE

    interface SettingsView {
        fun setVersion(version: String)
        fun showDeveloperOptions()
        fun showMockMode(mockMock: Boolean)
        fun doFinish()
    }

    fun onActivityCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        view.setVersion(BuildConfig.VERSION_NAME)
        if (buildType == "debug") {
            view.showDeveloperOptions()
            view.showMockMode(PrefUtils.isMockMode(preferences))
        }
    }

    fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                view.doFinish()
                true
            }
            else -> false
        }
    }

    fun onMockModeCheckedChanged(isChecked: Boolean) {
        PrefUtils.setMockMode(preferences, isChecked)
    }
}
