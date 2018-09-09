package com.leaguechampions.ui.champions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.data.repository.Status
import com.leaguechampions.databinding.ActivityChampionsBinding
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import com.leaguechampions.ui.championdetails.ChampionDetailsActivity
import com.leaguechampions.ui.settings.SettingsActivity
import com.leaguechampions.utils.EventObserver
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChampionsActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityChampionsBinding
    private lateinit var adapter: ChampionsAdapter
    private lateinit var viewModel: ChampionsViewModel

    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_champions)

        setSupportActionBar(binding.activityChampionsToolbar)
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
            Status.LOADING -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            Status.SUCCESS -> setAdapter(viewState.champions)
            Status.ERROR -> showError(viewState.error)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.menu_settings -> {
//                viewModel.onSettingsClicked()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
        return viewModel.onOptionsItemSelected(item)
    }

    private fun setAdapter(champions: List<Champion>) {
//        adapter = ChampionsAdapter(champions, object: ChampionsAdapter.OnItemClickListener {
//            override fun onItemClick(champion: Champion) {
//                viewModel.onChampionClicked(champion.id)
//            }
//        })
        adapter = ChampionsAdapter(champions) { champion ->
            viewModel.onChampionClicked(champion.id)
        }
        binding.activityChampionsRvChampions.adapter = adapter
    }

    private fun showDetails(championId: String) {
        startActivity(ChampionDetailsActivity.getIntent(this, championId))
    }

    private fun showSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

//    private fun showError(@StringRes stringId: Int) {
//        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
//    }
//
//    private fun showError(@StringRes stringId: Int, errorCode: Int) {
//        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show()
//    }
}
