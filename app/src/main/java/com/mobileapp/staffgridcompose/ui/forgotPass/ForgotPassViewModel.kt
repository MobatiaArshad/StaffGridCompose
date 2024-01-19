package com.mobileapp.staffgridcompose.ui.forgotPass

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ForgotPassViewModel: ViewModel() {

    var email = MutableStateFlow("").asStateFlow()
}