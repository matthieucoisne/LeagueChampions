package com.leaguechampions.ui.champions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.model.RiotResponse
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import com.leaguechampions.ui.championdetails.ChampionDetailsActivity
import com.leaguechampions.ui.settings.SettingsActivity
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChampionsActivity : DaggerAppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var rvChampions: RecyclerView

    private lateinit var adapter: ChampionsAdapter

    private lateinit var viewModel: ChampionsViewModel

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var picasso: Picasso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champions)

        toolbar = findViewById(R.id.activity_champions_toolbar)
        rvChampions = findViewById(R.id.activity_champions_rvChampions)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.app_name)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionsViewModel::class.java)
        viewModel.riotResponse.observe(this, Observer { riotResponseResource ->
            setAdapter(riotResponseResource?.data!!)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                showSettings()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setAdapter(riotResponse: RiotResponse) {
        adapter = ChampionsAdapter(riotResponse, picasso, object: ChampionsAdapter.OnItemClickListener {
            override fun onItemClick(version: String, champion: Champion) {
                showDetails(champion.id)
            }
        })
        rvChampions.adapter = adapter
        rvChampions.layoutManager = GridLayoutManager(this, 3)
    }

//    override fun showError(@StringRes stringId: Int) {
//        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
//    }
//
//    override fun showError(@StringRes stringId: Int, errorCode: Int) {
//        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show()
//    }

    private fun showDetails(championId: String) {
        startActivity(ChampionDetailsActivity.getIntent(this, championId))
    }

    private fun showSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}
