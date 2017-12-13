package com.leaguechampions.data.model

data class Champion(
        val id: String,
        val name: String,
        val title: String,
        val lore: String) : Comparable<Champion> {

    override fun compareTo(other: Champion): Int {
        return name.compareTo(other.name)
    }
}
