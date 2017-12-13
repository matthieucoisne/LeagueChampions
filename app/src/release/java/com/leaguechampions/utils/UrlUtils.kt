package com.leaguechampions.utils

import android.content.Context

object UrlUtils {

    fun getImageUrl(context: Context, version: String, championKey: String): String {
        return "http://ddragon.leagueoflegends.com/cdn/$version/img/champion/$championKey.png"
    }
}
