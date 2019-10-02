package com.leaguechampions.core.data.model

class RiotResponse<T>(
    val version: String,
    val data: Map<String, T>
)
