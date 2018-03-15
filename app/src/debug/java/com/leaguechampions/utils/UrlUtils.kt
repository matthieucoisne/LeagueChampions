package com.leaguechampions.utils

import android.content.Context
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion
import java.util.*

object UrlUtils {

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

    fun getImageUrl(context: Context, champion: Champion): String {
        var championId = champion.id
        return if (PrefUtils.isMockMode(context)) {
            val scheme = if (Const.isGlide) "http" else "mock"
            if (!champions.contains(championId)) {
                championId = champions[Random().nextInt(champions.size)]
            }

            "$scheme:///images/ic_poro_${championId.toLowerCase()}.png"
        } else {
            "${Const.URL_BASE}cdn/${champion.version}/img/champion/$championId.png"
        }
    }
}
