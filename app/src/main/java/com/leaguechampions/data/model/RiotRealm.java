package com.leaguechampions.data.model;

import com.google.gson.annotations.SerializedName;

public class RiotRealm {

    @SerializedName("n")
    private RiotVersion version;

    public String getVersion() {
        return version.champion;
    }

    private static class RiotVersion {
        String champion;
    }
}
