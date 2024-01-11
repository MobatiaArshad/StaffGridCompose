package com.mobileapp.staffgridcompose.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobileapp.staffgridcompose.ui.onboarding.model.PassingData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
class OnboardingViewModel: ViewModel() {
    private val _onBoardingStep= MutableLiveData(1)
    var onBoardingStep:LiveData<Int> = _onBoardingStep
    var passingData= MutableLiveData<PassingData>()
    private val _isLoaded = MutableLiveData(false)
    var isDataLoaded:LiveData<Boolean> = _isLoaded


    fun loadData(passedState:Boolean) {
        passingData.value= PassingData(
            firstName = "firstName",
            lastName = "lastName",
            city = "city",
            state = "state",
            postalCode = 0,
            cellNo = 0,
            email = "email",
        )
        _isLoaded.value = passedState
    }

    val isLoadedFlow = flow {
        // Simulate a 5-second delay
        delay(5000)
        emit(true)
    }

    fun changeScreen(step:Int){
        _onBoardingStep.value=step
    }
    fun returnScreen(){
        if (_onBoardingStep.value!! >1) _onBoardingStep.value = _onBoardingStep.value!! - 1
    }
}