package com.mobileapp.staffgridcompose.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {

    var email = MutableStateFlow("").asStateFlow()
    var password = MutableStateFlow("").asStateFlow()

}