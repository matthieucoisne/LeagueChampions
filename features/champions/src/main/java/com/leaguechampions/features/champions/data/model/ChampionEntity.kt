package com.leaguechampions.features.champions.data.model

import android.os.Build
import android.text.Html
import android.text.SpannedString
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leaguechampions.features.champions.domain.model.Champion

@Entity(tableName = "champions")
data class ChampionEntity(
    @PrimaryKey var id: String,
    val name: String,
    val title: String,
    var version: String = "",
    val lore: String?
) : Comparable<ChampionEntity> {

    override fun compareTo(other: ChampionEntity): Int {
        return name.compareTo(other.name)
    }
}

fun ChampionEntity.toChampion(): Champion {
    val lore = lore?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(it)
        }
    } ?: SpannedString("")

    return Champion(id, name, lore, version)
}

