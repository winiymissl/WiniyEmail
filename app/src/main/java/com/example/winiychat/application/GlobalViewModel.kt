package com.example.winiychat.application

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GlobalViewModel(val app: Application) : AndroidViewModel(app) {
    companion object {
        private var instance: GlobalViewModel? = null

        fun getInstance(application: Application): GlobalViewModel {
            if (instance == null) {
                instance = GlobalViewModel(application)
            }
            return instance!!
        }
    }

    private var configLiveData = MutableLiveData<EmailConfigInfo>()

    fun getConfigLiveData(): LiveData<EmailConfigInfo> {
        return configLiveData
    }

    fun requestConfigEmail(
        username: String,
        password: String,
        server: String,
        port: Int,
        protocol_receive: String,
        isSSLorTLS: Boolean
    ) {
        var temp = EmailConfigInfo()
        temp.username = username
        temp.password = password
        temp.server = server
        temp.port = port
        temp.protocol_receive = protocol_receive
        temp.isSSLorTLS = isSSLorTLS
        configLiveData.value = temp
    }

    open class UserInfo {
        companion object {
            private var instance: UserInfo? = null
            fun getUserInfoInstance(): UserInfo{
                if (instance == null) {
                    instance = UserInfo()
                }
                return instance!!
            }
        }

        var username: String? = null
        var password: String? = null
    }

    class EmailConfigInfo : UserInfo() {

        companion object {
            private var instance: EmailConfigInfo? = null
            fun getInstance(): EmailConfigInfo{
                if (instance == null) {
                    instance = EmailConfigInfo()
                }
                return instance!!
            }
        }

        var server: String? = null
        var port: Int? = null
        var protocol_receive: String? = null
        var isSSLorTLS: Boolean? = null
    }
}