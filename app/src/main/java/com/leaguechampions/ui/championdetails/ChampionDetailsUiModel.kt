package com.leaguechampions.ui.championdetails

import android.os.Build
import android.text.Html
import android.text.Spanned
import com.leaguechampions.data.model.Champion

data class ChampionDetailsUiModel(
        val id: String,
        val name: String,
        val lore: Spanned,
        val version: String
)

fun Champion.toChampionDetailsUiModel(): ChampionDetailsUiModel {
    val lore = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(lore, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(lore)
    }
    return ChampionDetailsUiModel(id, name, lore, version)
}
