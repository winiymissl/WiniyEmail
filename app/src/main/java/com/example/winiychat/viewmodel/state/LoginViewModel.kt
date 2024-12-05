package com.example.winiychat.viewmodel.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.winiychat.model.repository.LoginRepository

import com.example.winiychat.R
import com.example.winiychat.view.fragment.login.data.LoginFormState
import com.example.winiychat.view.fragment.login.data.LoginResult
import com.example.winiychat.extension_utils.Result
import com.example.winiychat.application.GlobalViewModel

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private var configEmail = MutableLiveData<GlobalViewModel.EmailConfigInfo>()

    val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        val result = loginRepository.login(username, password)
        if (result is Result.Success) {
//            _loginResult.value =
//                LoginResult(success = LoggedInUserView(displayName = LoggedInUserConfig.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun usernameDataChanged(username: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}