package com.mobileapp.staffgridcompose.ui.verifyEmail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VerifyEmailViewModel: ViewModel() {

    var code = MutableStateFlow("").asStateFlow()
}