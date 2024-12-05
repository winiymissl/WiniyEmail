package com.example.winiychat.model.repository

import com.example.winiychat.model.bean.LoggedInUserConfig
import com.example.winiychat.model.repository.remote.LoginRemoteSource
import com.example.winiychat.extension_utils.Result

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginRemoteSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUserConfig? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUserConfig> {
        // handle login
        val result = dataSource.login(username, password)
        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUserConfig) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}