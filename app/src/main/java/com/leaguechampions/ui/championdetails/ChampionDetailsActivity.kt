package com.leaguechampions.ui.championdetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import com.leaguechampions.R
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion
import com.leaguechampions.databinding.ActivityChampionDetailsBinding
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import com.leaguechampions.utils.EventObserver
import com.leaguechampions.utils.Status
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChampionDetailsActivity : DaggerAppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.activity_champion_details_toolbar) }

    private lateinit var binding: ActivityChampionDetailsBinding
    private lateinit var viewModel: ChampionDetailsViewModel

    @Inject lateinit var viewModelFactory: ViewModelFactory

    companion object {
        fun getIntent(context: Context, championId: String): Intent {
            val intent = Intent(context, ChampionDetailsActivity::class.java)
            intent.putExtra(Const.KEY_CHAMPION_ID, championId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_champion_details)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionDetailsViewModel::class.java)

        val championId = intent.getStringExtra(Const.KEY_CHAMPION_ID)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.app_name)

        viewModel.viewAction.observe(this, EventObserver {
            when (it) {
                is ChampionDetailsViewModel.ViewAction.Finish -> finish()
            }
        })

        viewModel.viewState.observe(this, Observer { state ->
            state?.let {
                render(it)
            }
        })

        viewModel.setChampionId(championId)
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

    private fun render(viewState: ChampionDetailsViewModel.ViewState) {
        when (viewState.status) {
            Status.LOADING -> showToast("Loading")
            Status.SUCCESS -> setChampion(viewState.champion!!)
            Status.ERROR -> showError(viewState.error)
        }
    }

    private fun setChampion(champion: Champion) {
        binding.champion = champion
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
