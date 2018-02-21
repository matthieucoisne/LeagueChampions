package com.leaguechampions.ui.champions;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.leaguechampions.data.model.Resource;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.data.repository.ChampionRepository;

import javax.inject.Inject;

public class ChampionsViewModel extends ViewModel {

    private LiveData<Resource<RiotResponse>> riotResponse;

    @Inject
    public ChampionsViewModel(ChampionRepository championRepository) {
        if (riotResponse == null) {
            riotResponse = championRepository.getChampions();
        }
    }

    public LiveData<Resource<RiotResponse>> getRiotResponse() {
        return riotResponse;
    }
}
