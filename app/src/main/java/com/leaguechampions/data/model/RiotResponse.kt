package com.leaguechampions.data.model

data class RiotResponse(
    val version: String,
    val data: Map<String, Champion>
)
