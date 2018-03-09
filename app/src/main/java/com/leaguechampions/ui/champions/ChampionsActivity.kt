package com.leaguechampions.ui.champions

import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.leaguechampions.R
import com.leaguechampions.data.model.RiotResponse
import com.leaguechampions.ui.championdetails.ChampionDetailsActivity
import com.leaguechampions.ui.settings.SettingsActivity
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChampionsActivity : DaggerAppCompatActivity(), ChampionsPresenter.ChampionsView {

    private lateinit var toolbar: Toolbar
    private lateinit var rvChampions: RecyclerView

    private lateinit var adapter: ChampionsAdapter

    @Inject lateinit var presenter: ChampionsPresenter
    @Inject lateinit var picasso: Picasso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champions)

        toolbar = findViewById(R.id.activity_champions_toolbar)
        rvChampions = findViewById(R.id.activity_champions_rvChampions)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.app_name)

        presenter.onActivityCreated(savedInstanceState, intent.extras)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return presenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun setAdapter(riotResponse: RiotResponse) {
        adapter = ChampionsAdapter(riotResponse, picasso, presenter)
        rvChampions.adapter = adapter
        rvChampions.layoutManager = GridLayoutManager(this, 3)
    }

    override fun showError(@StringRes stringId: Int) {
        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
    }

    override fun showError(@StringRes stringId: Int, errorCode: Int) {
        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show()
    }

    override fun showDetails(version: String, championId: String) {
        startActivity(ChampionDetailsActivity.getIntent(this, version, championId))
    }

    override fun showSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}
