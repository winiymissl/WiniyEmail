package com.example.winiychat.viewmodel.request

import com.jeremyliao.liveeventbus.core.LiveEvent

class VerifyEmailEvent constructor(val isSuccess: Boolean) : LiveEvent {

}