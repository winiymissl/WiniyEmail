package com.example.winiychat.application

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //热修复
    }
}