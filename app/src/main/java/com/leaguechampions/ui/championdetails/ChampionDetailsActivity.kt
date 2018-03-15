package com.leaguechampions.ui.championdetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.leaguechampions.R
import com.leaguechampions.data.local.Const
import com.leaguechampions.databinding.ActivityChampionDetailsBinding
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChampionDetailsActivity : DaggerAppCompatActivity() {

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

        setSupportActionBar(binding.activityChampionDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.app_name)

        val championId = intent.getStringExtra(Const.KEY_CHAMPION_ID)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionDetailsViewModel::class.java)
        viewModel.riotResponse.observe(this, Observer { riotResponseResource ->
            binding.champion = riotResponseResource?.data
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

//    override fun showError(@StringRes stringId: Int) {
//        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
//    }
//
//    override fun showError(@StringRes stringId: Int, errorCode: Int) {
//        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show()
//    }
}
