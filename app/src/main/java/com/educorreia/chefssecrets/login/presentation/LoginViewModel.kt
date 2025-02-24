package com.educorreia.chefssecrets.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authenticator: Authenticator
) : ViewModel() {
    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginWithGoogle -> {
                viewModelScope.launch {
                    authenticator.loginWithGoogle()
                }
            }
        }
    }
}