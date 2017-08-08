package com.leaguechampions.datasource.remote;

import com.leaguechampions.model.RiotResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion.json
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion/Riven.json
 */
public interface Api {
    @GET("champion.json")
    Call<RiotResponse> getChampions();

    @GET("champion/{championId}.json")
    Call<RiotResponse> getChampion(@Path("championId") String championId);
}
