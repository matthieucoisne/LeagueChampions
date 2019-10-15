package com.leaguechampions.features.settings.presentation.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.leaguechampions.features.settings.R
import com.leaguechampions.features.settings.databinding.ActivitySettingsBinding
import com.leaguechampions.libraries.core.BuildConfig
import com.leaguechampions.libraries.core.utils.PrefUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    @Inject lateinit var preferences: SharedPreferences

    // Workaround for BuildConfig.BUILD_TYPE in methods for Unit Tests
    private val buildType = BuildConfig.BUILD_TYPE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.settings)

        render()
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

    private fun render() {
        binding.tvVersion.text = String.format(getString(R.string.version), BuildConfig.VERSION_NAME)

        if (buildType == "debug") {
            binding.llyDeveloperOptions.visibility = View.VISIBLE
            binding.switchMockMode.isChecked = PrefUtils.isMockMode(preferences)
            binding.switchMockMode.setOnCheckedChangeListener { _, isChecked ->
                PrefUtils.setMockMode(preferences, isChecked)
            }
        }
    }
}
