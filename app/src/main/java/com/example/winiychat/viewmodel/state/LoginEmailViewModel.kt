package com.example.winiychat.viewmodel.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.winiychat.viewmodel.request.VerifyEmailEvent
import com.jeremyliao.liveeventbus.LiveEventBus
import java.util.regex.Pattern

class LoginEmailViewModel : ViewModel() {
    private var email = MutableLiveData<String>()
    fun getEmail(): LiveData<String> {
        return email
    }

    fun requestEmail(email: String) {
        this.email.value = verify(email)
    }

    private fun verify(email: String): String {
        LiveEventBus.get<VerifyEmailEvent>(VerifyEmailEvent::class.java)
            .post(VerifyEmailEvent(isQQMail(email)))
        return email
    }

    private fun isQQMail(email: String): Boolean {
        var regex = "\\d{5,11}@qq\\.com"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(email)
        return matcher.matches();
    }

}