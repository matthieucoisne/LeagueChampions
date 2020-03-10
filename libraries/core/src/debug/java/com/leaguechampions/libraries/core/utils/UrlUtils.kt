package com.leaguechampions.libraries.core.utils

import android.content.Context
import com.leaguechampions.libraries.core.data.local.Const
import java.util.Locale
import java.util.Random

class UrlUtils {
    companion object {
        private val championIds = arrayListOf(
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

        fun getImageUrl(context: Context, championId: String, version: String): String {
            var id = championId
            return if (PrefUtils.isMockMode(context)) {
                val scheme = if (Const.isGlide) "file:///android_asset/" else "mock:///"
                if (!championIds.contains(id)) {
                    id = championIds[Random().nextInt(championIds.size)]
                }

                "${scheme}images/ic_poro_${id.toLowerCase(Locale.getDefault())}.png"
            } else {
                "${Const.URL_BASE}cdn/$version/img/champion/$id.png"
            }
        }
    }
}
