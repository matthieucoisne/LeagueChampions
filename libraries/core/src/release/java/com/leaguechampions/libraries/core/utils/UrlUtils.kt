package com.leaguechampions.libraries.core.utils

import android.content.Context
import com.leaguechampions.libraries.core.data.local.Const

object UrlUtils {

    fun getImageUrl(context: Context, championId: String, version: String): String {
        return "${Const.URL_BASE}cdn/$version/img/champion/$championId.png"
    }
}
