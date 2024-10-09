package com.example.winiychat.model.bean

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUserConfig(
    val userId: String,
    val displayName: String
)