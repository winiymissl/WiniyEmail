package com.example.winiychat.viewmodel.application

import android.annotation.SuppressLint
import android.app.Application
import android.os.Parcel
import android.os.Parcelable
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

    private var configLiveData = MutableLiveData<UserInfo.EmailConfig>()

    fun getConfigLiveData(): LiveData<UserInfo.EmailConfig> {
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
        var temp = UserInfo.EmailConfig()
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
            fun getInstance(): UserInfo {
                if (instance == null) {
//                    instance = UserInfo.EmailConfig()
                }
                return UserInfo()
            }
        }

        var username: String? = null
        var password: String? = null

        class EmailConfig : UserInfo() {
            var server: String? = null
            var port: Int? = null
            var protocol_receive: String? = null
            var isSSLorTLS: Boolean? = null
        }
    }
}