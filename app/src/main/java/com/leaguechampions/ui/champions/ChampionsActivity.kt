package com.leaguechampions.ui.champions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import com.leaguechampions.ui.championdetails.ChampionDetailsActivity
import com.leaguechampions.ui.settings.SettingsActivity
import com.leaguechampions.utils.EventObserver
import com.leaguechampions.utils.Status
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChampionsActivity : DaggerAppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.activity_champions_toolbar) }
    private val rvChampions by lazy { findViewById<RecyclerView>(R.id.activity_champions_rvChampions) }

    private lateinit var adapter: ChampionsAdapter
    private lateinit var viewModel: ChampionsViewModel

    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champions)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.app_name)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionsViewModel::class.java)

        viewModel.viewAction.observe(this, EventObserver {
            when (it) {
                is ChampionsViewModel.ViewAction.ShowDetails -> showDetails(it.championId)
                is ChampionsViewModel.ViewAction.ShowSettings -> showSettings()
            }
        })

        viewModel.viewState.observe(this, Observer { state ->
            state?.let {
                render(it)
            }
        })
    }

    private fun render(viewState: ChampionsViewModel.ViewState) {
        when (viewState.status) {
            Status.LOADING -> showToast("Loading")
            Status.SUCCESS -> setAdapter(viewState.champions)
            Status.ERROR -> showError(viewState.error)
        }
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

    private fun setAdapter(champions: List<Champion>) {
        adapter = ChampionsAdapter(champions) { viewModel.onChampionClicked(it.id) }
        rvChampions.adapter = adapter
    }

    private fun showDetails(championId: String) {
        startActivity(ChampionDetailsActivity.getIntent(this, championId))
    }

    private fun showSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
