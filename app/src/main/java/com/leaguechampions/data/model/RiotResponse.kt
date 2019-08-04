package com.leaguechampions.data.model

class RiotResponse<T>(
    val version: String,
    val data: Map<String, T>
)
