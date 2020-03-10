package com.leaguechampions.features.champions.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.leaguechampions.features.champions.R
import com.leaguechampions.features.champions.databinding.ActivityChampionsBinding
import com.leaguechampions.features.champions.injection.ChampionComponent
import com.leaguechampions.features.champions.injection.ChampionComponentProvider

class ChampionsActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var championComponent: ChampionComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        championComponent = (application as ChampionComponentProvider).provideChampionComponent()
        super.onCreate(savedInstanceState)
        val binding = ActivityChampionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setTitle(R.string.app_name)

        navController = findNavController(R.id.activity_champions_navHostFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
