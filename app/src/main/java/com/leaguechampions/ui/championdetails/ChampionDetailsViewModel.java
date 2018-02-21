package com.leaguechampions.ui.championdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.leaguechampions.data.model.Resource;
import com.leaguechampions.data.model.RiotResponse;
import com.leaguechampions.data.repository.ChampionRepository;

import javax.inject.Inject;

public class ChampionDetailsViewModel extends ViewModel {

    private final LiveData<Resource<RiotResponse>> riotResponse;
    private final MutableLiveData<String> championId = new MutableLiveData<>();

    @Inject
    public ChampionDetailsViewModel(ChampionRepository championRepository) {
        riotResponse = Transformations.switchMap(championId, championId -> {
           if (championId == null) {
               // TODO return AbsentLiveData.create();
               return null;
           } else {
               return championRepository.getChampionDetails(championId);
           }
        });
    }

    public void setChampionId(String championId) {
        if (championId.equals(this.championId.getValue())) {
            return;
        }
        this.championId.setValue(championId);
    }

    public LiveData<Resource<RiotResponse>> getRiotResponse() {
        return riotResponse;
    }
}
