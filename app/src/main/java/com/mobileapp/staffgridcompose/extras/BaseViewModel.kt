package com.mobileapp.staffgridcompose.extras

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


    fun loadingStatus(input: Loading) {
        _loading.value = input
    }

    fun setErrorMsg(input: String) {
        _errorMsg.value = input
    }

}