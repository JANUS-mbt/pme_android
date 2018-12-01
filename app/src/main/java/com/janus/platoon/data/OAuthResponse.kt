package com.janus.platoon.data

data class OAuthResponse(
    val accessExp: String,
    val accessToken: String,
    val refreshToken: String,
    val userId: String,
    val username: String
)