package com.example.winiychat.view.fragment.login.data

import com.example.winiychat.model.LoggedInUserView

data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)