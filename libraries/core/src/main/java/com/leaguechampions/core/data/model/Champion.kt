package com.leaguechampions.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champions")
data class Champion(
        @PrimaryKey var id: String,
        val name: String,
        val title: String,
        var version: String = "",
        val lore: String
) : Comparable<Champion> {

    override fun compareTo(other: Champion): Int {
        return name.compareTo(other.name)
    }
}
