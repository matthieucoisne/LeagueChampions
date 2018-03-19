package com.leaguechampions.ui.champions;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.leaguechampions.data.model.Champion;
import com.leaguechampions.data.repository.ChampionRepository;
import com.leaguechampions.data.repository.Resource;

import java.util.Map;

import javax.inject.Inject;

public class ChampionsViewModel extends ViewModel {

    private LiveData<Resource<Map<String, Champion>>> riotResponse;

    @Inject
    public ChampionsViewModel(ChampionRepository championRepository) {
        riotResponse = championRepository.getChampions();
    }

    public LiveData<Resource<Map<String, Champion>>> getRiotResponse() {
        return riotResponse;
    }
}
