package com.mobileapp.staffgridcompose.ui.signUp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel : ViewModel() {

    private val _fName = MutableStateFlow("")
    val fName: StateFlow<String> = _fName.asStateFlow()


    var lname = MutableStateFlow("").asStateFlow()
    var email = MutableStateFlow("").asStateFlow()
    var password = MutableStateFlow("").asStateFlow()

    fun fNameTextChange(input: String) {
        _fName.value = input
    }
}