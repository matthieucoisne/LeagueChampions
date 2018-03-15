package com.leaguechampions.ui.champions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.leaguechampions.R
import com.leaguechampions.data.model.Champion
import com.leaguechampions.databinding.ActivityChampionsBinding
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import com.leaguechampions.ui.championdetails.ChampionDetailsActivity
import com.leaguechampions.ui.settings.SettingsActivity
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
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

    private fun setAdapter(data: Map<String, Champion>) {
        val champions: List<Champion> = ArrayList(data.values)
        Collections.sort(champions)
//        adapter = ChampionsAdapter(champions, object: ChampionsAdapter.OnItemClickListener {
//            override fun onItemClick(champion: Champion) {
//                showDetails(champion.id)
//            }
//        })
        adapter = ChampionsAdapter(champions, { champion -> showDetails(champion.id) })
        binding.activityChampionsRvChampions.adapter = adapter
    }

    private fun showDetails(championId: String) {
        startActivity(ChampionDetailsActivity.getIntent(this, championId))
    }

    private fun showSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

//    override fun showError(@StringRes stringId: Int) {
//        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
//    }
//
//    override fun showError(@StringRes stringId: Int, errorCode: Int) {
//        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show()
//    }
}
