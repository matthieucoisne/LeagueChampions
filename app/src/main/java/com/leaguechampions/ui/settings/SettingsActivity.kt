package com.leaguechampions.ui.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SwitchCompat
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.leaguechampions.LeagueChampions
import com.leaguechampions.R
import javax.inject.Inject

class SettingsActivity : AppCompatActivity(), SettingsPresenter.SettingsView {

    private lateinit var toolbar: Toolbar
    private lateinit var tvVersion: TextView
    private lateinit var llyDeveloperOptions: LinearLayout
    private lateinit var switchMockMode: SwitchCompat

    @Inject lateinit var presenter: SettingsPresenter

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

        switchMockMode.setOnCheckedChangeListener {
            _, isChecked -> presenter.onMockModeCheckedChanged(isChecked)
        }

        DaggerSettingsComponent
                .builder()
                .appComponent((applicationContext as LeagueChampions).appComponent)
                .settingsPresenterModule(SettingsPresenterModule(this))
                .build()
                .inject(this)

        presenter.onActivityCreated(savedInstanceState, intent.extras)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return presenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun setVersion(version: String) {
        tvVersion.text = String.format(getString(R.string.version), version)
    }

    override fun showDeveloperOptions() {
        llyDeveloperOptions.visibility = View.VISIBLE
    }

    override fun showMockMode(mockMock: Boolean) {
        switchMockMode.isChecked = mockMock
    }

    override fun doFinish() {
        finish()
    }
}
