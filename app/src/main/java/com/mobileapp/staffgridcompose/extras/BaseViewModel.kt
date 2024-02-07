package com.mobileapp.staffgridcompose.extras

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mobileapp.staffgridcompose.utils.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel: ViewModel() {

    private val _loading = MutableStateFlow(Loading.Idle)
    val loading: StateFlow<Loading> = _loading.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg: StateFlow<String> = _errorMsg.asStateFlow()

    var showSnack by mutableStateOf(false)
    private val _snackMsg = MutableStateFlow("")
    val snackMsg: StateFlow<String> = _snackMsg.asStateFlow()


    fun showSnack(show: Boolean,msg: String) {
        showSnack = show
        _snackMsg.value = msg
    }

    fun resetSnack() {
        showSnack = false
        _snackMsg.value = ""
    }

    fun loadingStatus(input: Loading) {
        _loading.value = input
    }

    fun setErrorMsg(input: String) {
        _errorMsg.value = input
    }

}