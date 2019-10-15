package com.leaguechampions.features.champions.presentation.champions

import com.leaguechampions.features.champions.domain.model.Champion

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
