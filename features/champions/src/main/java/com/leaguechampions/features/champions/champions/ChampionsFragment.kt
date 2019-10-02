package com.leaguechampions.features.champions.champions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.leaguechampions.core.data.local.Const
import com.leaguechampions.core.injection.ViewModelFactory
import com.leaguechampions.core.utils.EventObserver
import com.leaguechampions.core.utils.Router
import com.leaguechampions.features.champions.R
import com.leaguechampions.features.champions.databinding.FragmentChampionsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ChampionsFragment : DaggerFragment() {

    private lateinit var adapter: ChampionsAdapter
    private lateinit var binding: FragmentChampionsBinding
    private lateinit var viewModel: ChampionsViewModel

    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChampionsBinding.inflate(inflater)

        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ChampionsViewModel::class.java)

        viewModel.viewAction.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                is ChampionsViewModel.ViewAction.NavigateToDetails -> navigateToDetails(it.championId)
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner) {
            render(it)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                navigateToSettings()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun render(viewState: ChampionsViewModel.ViewState) {
        when (viewState) {
            is ChampionsViewModel.ViewState.ShowLoading -> showToast("Loading")
            is ChampionsViewModel.ViewState.ShowChampions -> setAdapter(viewState.champions)
            is ChampionsViewModel.ViewState.ShowError -> showError(getString(viewState.errorStringId))
        }
    }

    private fun setAdapter(champions: ChampionsUiModel) {
        adapter = ChampionsAdapter(champions) {
            viewModel.onChampionClicked(it.id)
        }
        binding.rvChampions.adapter = adapter
    }

    private fun navigateToDetails(championId: String) {
        findNavController().navigate(
            R.id.action_championsFragment_to_championDetailsFragment,
            bundleOf(Const.KEY_CHAMPION_ID to championId)
        )
    }

    private fun navigateToSettings() {
        startActivity(Router.getSettingsIntent(requireContext()))
    }

    private fun showError(message: String) {
        showToast(message, Toast.LENGTH_LONG)
    }

    private fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), message, length).show()
    }
}
