package com.leaguechampions.data.remote;

import com.leaguechampions.data.model.RiotRealm;
import com.leaguechampions.data.model.RiotResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * http://ddragon.leagueoflegends.com/realms/na.json
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion.json
 * http://ddragon.leagueoflegends.com/cdn/7.15.1/data/en_US/champion/Riven.json
 */
public interface Api {

    @GET("realms/na.json")
    Observable<RiotRealm> getVersion();

    @GET("cdn/{version}/data/en_US/champion.json")
    Observable<RiotResponse> getChampions(@Path("version") String version);

    @GET("cdn/{version}/data/en_US/champion/{championId}.json")
    Observable<RiotResponse> getChampion(@Path("version") String version, @Path("championId") String championId);
}