package com.leaguechampions.ui.championdetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.leaguechampions.R
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject



class ChampionDetailsActivity : DaggerAppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var ivChampion: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvLore: TextView

    private lateinit var viewModel: ChampionDetailsViewModel

    @Inject lateinit var viewModelFactory: ViewModelFactory

    companion object {
        fun getIntent(context: Context, version: String, championId: String): Intent {
            val intent = Intent(context, ChampionDetailsActivity::class.java)
            intent.putExtra(Const.KEY_VERSION, version)
            intent.putExtra(Const.KEY_CHAMPION_ID, championId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champion_details)

        toolbar = findViewById(R.id.activity_champion_details_toolbar)
        ivChampion = findViewById(R.id.activity_champion_details_ivChampion)
        tvName = findViewById(R.id.activity_champion_details_tvName)
        tvLore = findViewById(R.id.activity_champion_details_tvLore)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.app_name)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionDetailsViewModel::class.java)
        viewModel.setChampionId("Riven")
        viewModel.getRiotResponse().observe(this, Observer { riotResponseResource ->
            showDetails("7.15.1", riotResponseResource?.data?.data?.get("Riven"))
        })
    }

//    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
//        presenter.onSaveInstanceState(outState)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return presenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
//    }
//
    fun showDetails(version: String, champion: Champion?) {
        tvName.text = champion?.name
        tvLore.text = Html.fromHtml(champion?.lore)
    }
//
//    override fun showError(@StringRes stringId: Int) {
//        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
//    }
//
//    override fun showError(@StringRes stringId: Int, errorCode: Int) {
//        Toast.makeText(this, String.format(getString(stringId), errorCode), Toast.LENGTH_SHORT).show()
//    }
//
//    override fun doFinish() {
//        finish()
//    }
}
