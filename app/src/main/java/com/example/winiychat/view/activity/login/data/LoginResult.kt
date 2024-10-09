package com.example.winiychat.view.activity.login.data

import com.example.winiychat.view.activity.login.data.model.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)