package com.leaguechampions.features.champions.data.model

import android.os.Build
import android.text.Html
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leaguechampions.features.champions.domain.model.Champion

@Entity(tableName = "ChampionDetails")
data class ChampionDetailsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val title: String,
//    var tags: List<String>,
    val lore: String,
    @Embedded(prefix = "info_") val info: Info,
    @Embedded(prefix = "stats_") val stats: Stats,
    var version: String = ""
)

data class Info(
    val attack: Int,
    val defense: Int,
    val magic: Int,
    val difficulty: Int
)

data class Stats(
    val hp: Double,
    val hpPerLevel: Double,
    val mp: Double,
    val mpPerLevel: Double,
    val moveSpeed: Double,
    val armor: Double,
    val armorPerLevel: Double,
    val spellBlock: Double,
    val spellBlockPerLevel: Double,
    val attackRange: Int,
    val hpRegen: Double,
    val hpRegenPerLevel: Double,
    val mpRegen: Double,
    val mpRegenPerLevel: Double,
    val crit: Double,
    val critPerLevel: Double,
    val attackDamage: Double,
    val attackDamagePerLevel: Double,
    val attackSpeedPerLevel: Double,
    val attackSpeed: Double
)

fun ChampionDetailsEntity.toChampion(): Champion {
    val lore = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(lore, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(lore)
    }

    return Champion(id, name, lore, version)
}
