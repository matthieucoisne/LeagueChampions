package com.leaguechampions.features.champions.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leaguechampions.features.champions.domain.model.Champion

@Entity(tableName = "Champions")
data class ChampionEntity(
    @PrimaryKey var id: String,
    val name: String,
//    val tags: List<String>,
    val version: String
)

fun ChampionEntity.toChampion(): Champion {
    return Champion(id, name, null, version)
}
