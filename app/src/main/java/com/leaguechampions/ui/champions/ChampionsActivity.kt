package com.leaguechampions.ui.champions

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.leaguechampions.R
import com.leaguechampions.databinding.ActivityChampionsBinding
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import com.leaguechampions.ui.settings.SettingsActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChampionsActivity : DaggerAppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var rvChampions: RecyclerView

    private lateinit var adapter: ChampionsAdapter
    private lateinit var binding: ActivityChampionsBinding
    private lateinit var viewModel: ChampionsViewModel

    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champions)
//        toolbar = binding.activityChampionsToolbar
//        rvChampions = binding.activityChampionsRvChampions

//        setSupportActionBar(toolbar)
//        supportActionBar?.setTitle(R.string.app_name)

//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionsViewModel::class.java)
//
//        viewModel.viewAction.observe(this, EventObserver {
//            when (it) {
//                is ChampionsViewModel.ViewAction.ShowDetails -> showDetails(it.championId)
//                is ChampionsViewModel.ViewAction.ShowSettings -> showSettings()
//            }
//        })
//
//        viewModel.viewState.observe(this, Observer { state ->
//            state?.let { render(it) }
//        })
    }

//    private fun render(viewState: ChampionsViewModel.ViewState) {
//        when (viewState.status) {
//            Status.LOADING -> showToast("Loading")
//            Status.SUCCESS -> setAdapter(viewState.champions)
//            Status.ERROR -> showError(viewState.error)
//        }
//    }

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

//    private fun setAdapter(champions: List<Champion>) {
//        adapter = ChampionsAdapter(champions) { viewModel.onChampionClicked(it.id) }
//        rvChampions.adapter = adapter
//    }
//
//    private fun showDetails(championId: String) {
//        findNavController(R.id.activity_champions_navHostFragment).navigate(R.id.championDetailsActivity, bundleOf(Const.KEY_CHAMPION_ID to championId))
//    }

    private fun showSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

//    private fun showError(message: String) {
//        showToast(message, Toast.LENGTH_LONG)
//    }
//
//    private fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
//        Toast.makeText(this, message, length).show()
//    }


//    class LoginFragment : Fragment() {
//        class LoginViewModel : ViewModel()
//
//        private val viewModel: LoginViewModel by activityViewModels()
//    }
}
