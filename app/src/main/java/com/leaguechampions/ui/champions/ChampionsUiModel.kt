package com.leaguechampions.ui.champions

import com.leaguechampions.data.model.Champion

data class ChampionsUiModel(
        val champions: List<ChampionUiModel>
)

data class ChampionUiModel(
        val id: String,
        val name: String,
        val version: String
)

fun List<Champion>.toChampionsUiModel(): ChampionsUiModel {
    return ChampionsUiModel(map { ChampionUiModel(it.id, it.name, it.version) })
}
