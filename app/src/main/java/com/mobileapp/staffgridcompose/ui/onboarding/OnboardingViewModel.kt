package com.mobileapp.staffgridcompose.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileapp.staffgridcompose.ui.onboarding.model.PassingData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class OnboardingViewModel: ViewModel() {

    private var passingData= MutableStateFlow(PassingData())
    var passedData: StateFlow<PassingData> = passingData

    //check whether the api call is completed
    private val _isUploaded = MutableStateFlow(false)
    var isDataLoaded:StateFlow<Boolean> = _isUploaded

    private val _isLoading = MutableStateFlow(false)
    var isLoading:StateFlow<Boolean> = _isLoading

    private val delayedDataFlow = flow {
            delay(5000L)
            emit(PassingData(
                firstName = "firstName",
                lastName = "lastName",
                city = "city",
                state = "state",
                postalCode = 68555,
                cellNo = 90486756546,
                email = "email",
                streetAddress = "Street Address 1",
                streetAddress2 = "Street Address 2",
                emergencyContactNumber = 5645764435))

    }

    fun uploadDataToApi(passingData: PassingData){
        println("DATA= $passingData")
        triggerDelayedFlow()
    }

    init {
        assignValue()
    }
    private fun assignValue(){
        viewModelScope.launch {
            delayedDataFlow.collectLatest { data ->
                passingData.value = data
                println("DATA= ${passedData.value}")
            }
        }
    }

    private fun triggerDelayedFlow() {
        viewModelScope.launch {
            changeBoolean(true)
            delay(5000L)
            changeBoolean(false)
            _isUploaded.value = true
        }
    }
    private fun changeBoolean(value: Boolean) {
        _isLoading.value = value
    }



}