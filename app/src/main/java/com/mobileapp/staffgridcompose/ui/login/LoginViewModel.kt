package com.mobileapp.staffgridcompose.ui.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.mobileapp.staffgridcompose.extras.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: BaseViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun emailTextChange(input: String) {
        _email.value = input
    }

    fun passwordTextChange(input: String) {
        _password.value = input
    }
}