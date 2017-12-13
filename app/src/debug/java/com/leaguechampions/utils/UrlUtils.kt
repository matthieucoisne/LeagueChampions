package com.leaguechampions.utils

import android.content.Context
import com.leaguechampions.data.local.Const
import java.util.*

object UrlUtils {

    private val rand = Random()
    private val champions = arrayListOf(
            "Amumu",
            "Corki",
            "Garen",
            "Graves",
            "JarvanIV",
            "LeeSin",
            "Lulu",
            "MissFortune",
            "Nasus",
            "Riven",
            "Shaco",
            "Sona",
            "Teemo",
            "Tryndamere",
            "Vladimir",
            "Zed",
            "Ziggs"
    )

    fun getImageUrl(context: Context, version: String, championKey: String): String {
        var key = championKey
        return if (PrefUtils.isMockMode(context)) {
            val scheme = if (Const.isGlide) "http" else "mock"
            if (!champions.contains(key)) {
                key = champions[rand.nextInt(champions.size)]
            }

            "$scheme:///images/ic_poro_${key.toLowerCase()}.png"
        } else {
            "http://ddragon.leagueoflegends.com/cdn/$version/img/champion/$key.png"
        }
    }
}
