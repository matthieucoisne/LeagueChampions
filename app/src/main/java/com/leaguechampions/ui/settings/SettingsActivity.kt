package com.leaguechampions.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.SwitchCompat
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.leaguechampions.BuildConfig
import com.leaguechampions.R
import com.leaguechampions.utils.PrefUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvVersion: TextView
    private lateinit var llyDeveloperOptions: LinearLayout
    private lateinit var switchMockMode: SwitchCompat

    @Inject lateinit var preferences: SharedPreferences

    // Workaround for BuildConfig.BUILD_TYPE in methods for Unit Tests
    private val buildType = BuildConfig.BUILD_TYPE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        toolbar = findViewById(R.id.activity_settings_toolbar)
        tvVersion = findViewById(R.id.activity_settings_tvVersion)
        llyDeveloperOptions = findViewById(R.id.activity_settings_llyDeveloperOptions)
        switchMockMode = findViewById(R.id.activity_settings_switchMockMode)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.settings)

        tvVersion.text = String.format(getString(R.string.version), BuildConfig.VERSION_NAME)

        if (buildType == "debug") {
            llyDeveloperOptions.visibility = View.VISIBLE
            switchMockMode.isChecked = PrefUtils.isMockMode(preferences)
            switchMockMode.setOnCheckedChangeListener {
                _, isChecked -> PrefUtils.setMockMode(preferences, isChecked)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
