package com.leaguechampions.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champions")
data class Champion(
        @PrimaryKey var id: String,
        var name: String,
        var title: String,
        var version: String?,
        var lore: String
) : Comparable<Champion> {

//    @Suppress("DEPRECATION")
//    fun getLore(): Spanned {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Html.fromHtml(lore, Html.FROM_HTML_MODE_LEGACY)
//        } else {
//            Html.fromHtml(lore)
//        }
//    }

    override fun compareTo(other: Champion): Int {
        return name.compareTo(other.name)
    }
}
