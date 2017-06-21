package com.leaguechampions.datasource.remote;

import com.leaguechampions.model.Champion;
import com.leaguechampions.model.Champions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * https://na1.api.riotgames.com/lol/static-data/v3/champions
 * https://na1.api.riotgames.com/lol/static-data/v3/champions/92?champData=lore&champData=stats
 *
 * https://discussion.developer.riotgames.com/articles/1326/request-validation-changes.html
 */
public interface Api {
    @GET("lol/static-data/v3/champions")
    Call<Champions> getChampions();

    @GET("lol/static-data/v3/champions/{championId}?champData=lore&champData=stats")
    Call<Champion> getChampion(@Path("championId") int championId);
}
