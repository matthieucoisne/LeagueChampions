package com.leaguechampions.data.model

import android.os.Build
import android.text.Html
import android.text.Spanned

data class Champion(
        val id: String,
        val name: String,
        val title: String,
        var version: String?,
        private val lore: String) : Comparable<Champion> {

    @Suppress("DEPRECATION")
    fun getLore(): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(lore, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(lore)
        }
    }

    override fun compareTo(other: Champion): Int {
        return name.compareTo(other.name)
    }
}
