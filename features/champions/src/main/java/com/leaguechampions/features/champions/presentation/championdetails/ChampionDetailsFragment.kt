package com.leaguechampions.features.champions.presentation.championdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leaguechampions.libraries.core.data.local.Const
import com.leaguechampions.libraries.core.injection.ViewModelFactory
import com.leaguechampions.libraries.core.utils.loadChampionImage
import com.leaguechampions.features.champions.databinding.FragmentChampionDetailsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ChampionDetailsFragment : DaggerFragment() {

    private lateinit var binding: FragmentChampionDetailsBinding

    private lateinit var viewModel: ChampionDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChampionDetailsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ChampionDetailsViewModel::class.java)

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            render(it)
        })

        val championId = requireArguments().getString(Const.KEY_CHAMPION_ID)
        viewModel.setChampionId(championId)

        return binding.root
    }

    private fun render(viewState: ChampionDetailsViewModel.ViewState) {
        when (viewState) {
            is ChampionDetailsViewModel.ViewState.ShowLoading -> showToast("Loading")
            is ChampionDetailsViewModel.ViewState.ShowChampionDetails -> showChampionDetails(viewState.championDetails)
            is ChampionDetailsViewModel.ViewState.ShowError -> showError(getString(viewState.errorStringId))
        }
    }

    private fun showChampionDetails(championDetails: ChampionDetailsUiModel) {
        binding.tvName.text = championDetails.name
        binding.tvLore.text = championDetails.lore
        loadChampionImage(binding.ivChampion, championDetails.id, championDetails.version)
    }

    private fun showError(message: String) {
        showToast(message, Toast.LENGTH_LONG)
    }

    private fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireActivity(), message, length).show()
    }
}
