package com.leaguechampions.libraries.core.data.model

class RiotResponse<T>(
    val version: String,
    val data: Map<String, T>
)
