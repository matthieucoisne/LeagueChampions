package com.leaguechampions.features.champions.domain.model

import android.text.Spanned

data class Champion(
    val id: String,
    val name: String,
    val lore: Spanned?,
    val version: String
) : Comparable<Champion> {

    override fun compareTo(other: Champion): Int {
        return name.compareTo(other.name)
    }
}

