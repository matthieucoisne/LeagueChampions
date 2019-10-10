package com.leaguechampions.features.champions.presentation.championdetails

import android.text.Spanned
import com.leaguechampions.features.champions.domain.model.Champion

data class ChampionDetailsUiModel(
    val id: String,
    val name: String,
    val lore: Spanned?,
    val version: String
)

fun Champion.toChampionDetailsUiModel(): ChampionDetailsUiModel {
    return ChampionDetailsUiModel(id, name, lore, version)
}
