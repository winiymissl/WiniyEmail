package com.example.winiychat.viewmodel.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InBoxFragmentViewModel : ViewModel() {
    private val listResult = MutableLiveData<Result<List<String>>>()

    fun requestListData() {
        //先从数据库中访问，如果没有，则进行网络请求
        val res = Result.success(listOf("1", "2"))

    }
}