package com.example.winiychat.model.repository.remote

import com.example.winiychat.model.bean.LoggedInUserConfig
import com.example.winiychat.extension_utils.Result
import java.io.IOException
import java.util.UUID

class LoginRemoteSource {
    fun login(username: String, password: String): Result<LoggedInUserConfig> {
        try {
            val fakeUser = LoggedInUserConfig(UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}