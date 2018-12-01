package com.janus.platoon.data

data class User(
    val createdDate: String,
    val email: String,
    val enabled: Boolean,
    val identifier: String,
    val lastModifiedDate: String,
    val roles: List<String>,
    val rowVersion: Int,
    val username: String
)