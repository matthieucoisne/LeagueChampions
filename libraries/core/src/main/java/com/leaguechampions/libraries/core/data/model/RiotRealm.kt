package com.leaguechampions.libraries.core.data.model

import com.google.gson.annotations.SerializedName

class RiotRealm(
    @SerializedName("n")
    private val version: RiotVersion
) {

    fun getVersion(): String {
        return version.champion
    }

    class RiotVersion {
        internal lateinit var champion: String
    }
}
