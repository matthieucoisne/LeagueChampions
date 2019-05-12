package com.leaguechampions.ui.championdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leaguechampions.R
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion
import com.leaguechampions.databinding.FragmentChampionDetailsBinding
import com.leaguechampions.injection.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ChampionDetailsFragment : DaggerFragment() {

    private lateinit var binding: FragmentChampionDetailsBinding
    private lateinit var viewModel: ChampionDetailsViewModel

    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_champion_details, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChampionDetailsViewModel::class.java)

        viewModel.viewState.observe(this, Observer {
            when (it) {
                is ChampionDetailsViewModel.ViewState.ShowLoading -> showToast("Loading")
                is ChampionDetailsViewModel.ViewState.ShowChampion -> setChampion(it.champion)
                is ChampionDetailsViewModel.ViewState.ShowError -> showError(getString(it.errorStringId))
            }
        })

        val championId = arguments!!.getString(Const.KEY_CHAMPION_ID)
        viewModel.setChampionId(championId)

        return binding.root
    }

    private fun setChampion(champion: Champion) {
        binding.champion = champion
    }

    private fun showError(message: String) {
        showToast(message, Toast.LENGTH_LONG)
    }

    private fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireActivity(), message, length).show()
    }
}
