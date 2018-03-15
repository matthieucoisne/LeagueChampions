package com.leaguechampions.utils

import android.content.Context
import com.leaguechampions.data.local.Const
import com.leaguechampions.data.model.Champion

object UrlUtils {

    fun getImageUrl(context: Context, champion: Champion): String {
        return "${Const.URL_BASE}cdn/${champion.version}/img/champion/${champion.id}.png"
    }
}
