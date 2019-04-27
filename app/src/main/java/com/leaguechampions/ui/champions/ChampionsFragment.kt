package com.leaguechampions.ui.champions

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.leaguechampions.R
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion
import com.leaguechampions.databinding.FragmentChampionsBinding
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import com.leaguechampions.ui.settings.SettingsActivity
import com.leaguechampions.utils.EventObserver
import com.leaguechampions.utils.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ChampionsFragment : DaggerFragment() {

    private lateinit var rvChampions: RecyclerView

    private lateinit var adapter: ChampionsAdapter
    private lateinit var binding: FragmentChampionsBinding
    private lateinit var viewModel: ChampionsViewModel

    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_champions, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvChampions = binding.fragmentChampionsRvChampions

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionsViewModel::class.java)

        viewModel.viewAction.observe(this, EventObserver {
            when (it) {
                is ChampionsViewModel.ViewAction.ShowDetails -> showDetails(it.championId)
                is ChampionsViewModel.ViewAction.ShowSettings -> showSettings()
            }
        })

        viewModel.viewState.observe(this, Observer { state ->
            state?.let { render(it) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
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

    private fun render(viewState: ChampionsViewModel.ViewState) {
        when (viewState.status) {
            Status.LOADING -> showToast("Loading")
            Status.SUCCESS -> setAdapter(viewState.champions)
            Status.ERROR -> showError(viewState.error)
        }
    }

    private fun setAdapter(champions: List<Champion>) {
        adapter = ChampionsAdapter(champions) { viewModel.onChampionClicked(it.id) }
        rvChampions.adapter = adapter
    }

    private fun showDetails(championId: String) {
        findNavController().navigate(R.id.championDetailsFragment, bundleOf(Const.KEY_CHAMPION_ID to championId))
    }

    private fun showSettings() {
        startActivity(Intent(requireContext(), SettingsActivity::class.java))
    }

    private fun showError(message: String) {
        showToast(message, Toast.LENGTH_LONG)
    }

    private fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), message, length).show()
    }
}
